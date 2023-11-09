package io.zbhavyai.service;

import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.util.KeyUtils;
import io.zbhavyai.model.KeyPairWithID;
import io.zbhavyai.model.RSAJSONWebKey;
import io.zbhavyai.model.RSAJSONWebKeySet;
import io.zbhavyai.model.RSAJSONWebKeySetWithID;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForgeCraftInternalImpl implements ForgeCraft {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgeCraftInternalImpl.class);

    @Override
    public KeyPairWithID generateKeyPair(String keyID) {
        LOGGER.info("generateKeyPair: {}", keyID);

        try {
            return new KeyPairWithID(
                    keyID,
                    KeyUtils.generateKeyPair(2048));
        } catch (Exception e) {
            LOGGER.error("Error generating key pair", e);
            return null;
        }
    }

    @Override
    public List<String> serializeKeyPair(KeyPairWithID keyPair) {
        LOGGER.info("serializeKeyPair: {}", keyPair.getKeyID());

        return List.of(
                keyPair.getKeyID(),
                Base64.getEncoder().encodeToString(keyPair.getKeyPair().getPublic().getEncoded()),
                Base64.getEncoder().encodeToString(keyPair.getKeyPair().getPrivate().getEncoded()));
    }

    @Override
    public List<String> serializeKeyPairToPEM(KeyPairWithID keyPair) {
        LOGGER.info("serializeKeyPairToPEM: {}", keyPair.getKeyID());

        List<String> serializedKeyPair = new ArrayList<>();

        serializedKeyPair.add(keyPair.getKeyID());

        try (StringWriter stringWriter = new StringWriter(); JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter)) {
            pemWriter.writeObject(keyPair.getKeyPair().getPublic());
            pemWriter.flush();
            serializedKeyPair.add(stringWriter.toString());

            // reset StringWriter buffer
            stringWriter.getBuffer().setLength(0);

            pemWriter.writeObject(keyPair.getKeyPair().getPrivate());
            pemWriter.flush();
            serializedKeyPair.add(stringWriter.toString());
        } catch (Exception e) {
            LOGGER.error("Error serializing key pair", e);
        }

        return serializedKeyPair;
    }

    @Override
    public KeyPairWithID deserializeKeyPair(String keyID, String publicKeyInBase64, String privateKeyInBase64) {
        LOGGER.info("loadKeyPair: {}", keyID);

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyInBase64);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyInBase64);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey consPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PrivateKey consPrivateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            return new KeyPairWithID(keyID, new KeyPair(consPublicKey, consPrivateKey));
        } catch (Exception e) {
            LOGGER.error("Error loading key pair", e);
            return null;
        }
    }

    @Override
    public String createJwtToken(String keyID, String clientID, String tokenURL, KeyPairWithID keyPair) {
        return Jwt.claims()
                .issuer(clientID)
                .subject(clientID)
                .audience(tokenURL)
                .jws()
                .keyId(keyID)
                .algorithm(SignatureAlgorithm.RS256).sign(keyPair.getKeyPair().getPrivate());
    }

    @Override
    public X509Certificate generateX509Certificate(KeyPairWithID keyPair, String issuer, String subject) {
        Security.addProvider(new BouncyCastleProvider());

        // Generate X.509 certificate
        X500Name issuerName = new X500Name("CN=" + issuer);
        X500Name subjectName = new X500Name("CN=" + subject);

        try {
            ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSA")
                    .build(keyPair.getKeyPair().getPrivate());

            X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                    issuerName,
                    BigInteger.valueOf(1L),
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000),
                    subjectName,
                    keyPair.getKeyPair().getPublic());

            X509Certificate certificate = new JcaX509CertificateConverter()
                    .setProvider("BC")
                    .getCertificate(certBuilder.build(contentSigner));

            certificate.verify(keyPair.getKeyPair().getPublic());
            return certificate;
        } catch (Exception e) {
            LOGGER.error("Error generating X509 certificate", e);
            return null;
        }
    }

    @Override
    public RSAJSONWebKeySetWithID generateJSONWebKeySet(KeyPairWithID keyPair, String clientID) {
        LOGGER.info("generateJSONWebKeySet: {}", clientID);

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getKeyPair().getPublic();

        final RSAJSONWebKey jwk = new RSAJSONWebKey(
                keyPair.getKeyID(),
                "RSA",
                Base64.getUrlEncoder().withoutPadding().encodeToString(publicKey.getModulus().toByteArray()),
                Base64.getUrlEncoder().withoutPadding().encodeToString(publicKey.getPublicExponent().toByteArray()));

        return new RSAJSONWebKeySetWithID(clientID, new RSAJSONWebKeySet(List.of(jwk)));
    }
}
