package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyAndCertificate {

    private final String keyID;
    private final String publicKey;
    private final String privateKey;
    private final String certificate;

    @JsonCreator
    public KeyAndCertificate(
            @JsonProperty("keyID") String keyID,
            @JsonProperty("publicKey") String publicKey,
            @JsonProperty("privateKey") String privateKey,
            @JsonProperty("certificate") String certificate) {

        this.keyID = keyID;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    @JsonProperty("keyID")
    public String getKeyID() {
        return this.keyID;
    }

    @JsonProperty("publicKey")
    public String getPublicKey() {
        return this.publicKey;
    }

    @JsonProperty("privateKey")
    public String getPrivateKey() {
        return this.privateKey;
    }

    @JsonProperty("certificate")
    public String getCertificate() {
        return this.certificate;
    }
}
