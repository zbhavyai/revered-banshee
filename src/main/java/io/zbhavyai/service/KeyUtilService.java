package io.zbhavyai.service;

import io.zbhavyai.model.JSONWebKeySet;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

public interface KeyUtilService {

    public KeyPair generateKey(String key);

    public String createJwtToken(String subject, String issuer, String audience, long ttlMillis, KeyPair keyPair);

    public X509Certificate generateX509Certificate(KeyPair keyPair);

    public JSONWebKeySet generateJwks(KeyPair keyPair, String clientId);
}
