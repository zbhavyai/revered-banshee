package io.zbhavyai.service;

import io.zbhavyai.model.KeyPairWithID;
import io.zbhavyai.model.RSAJSONWebKeySetWithID;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

public interface ForgeCraft {

    public KeyPairWithID generateKeyPair(String keyID);

    public String[] serializeKeyPair(KeyPairWithID keyPair);

    public KeyPairWithID deserializeKeyPair(String keyID, String publicKeyInBase64, String privateKeyInBase64);

    public String createJwtToken(String keyID, String clientID, String tokenURL, KeyPairWithID keyPair);

    public X509Certificate generateX509Certificate(KeyPairWithID keyPair, String issuer, String subject);

    public RSAJSONWebKeySetWithID generateJSONWebKeySet(KeyPairWithID keyPair, String clientID);
}
