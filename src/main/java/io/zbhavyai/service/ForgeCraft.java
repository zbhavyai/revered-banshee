package io.zbhavyai.service;

import java.security.cert.X509Certificate;
import java.util.List;

import io.zbhavyai.model.KeyPairWithID;
import io.zbhavyai.model.RSAJSONWebKeySetWithID;

public interface ForgeCraft {

    public KeyPairWithID generateKeyPair(String keyID);

    public List<String> serializeKeyPair(KeyPairWithID keyPair);

    public List<String> serializeKeyPairToPEM(KeyPairWithID keyPair);

    public KeyPairWithID deserializeKeyPair(String keyID, String publicKeyInBase64, String privateKeyInBase64);

    public String createJwtToken(KeyPairWithID keyPair, String clientID, String tokenURL);

    public X509Certificate generateX509Certificate(KeyPairWithID keyPair, String issuer, String subject);

    public RSAJSONWebKeySetWithID generateJSONWebKeySet(KeyPairWithID keyPair, String clientID);
}
