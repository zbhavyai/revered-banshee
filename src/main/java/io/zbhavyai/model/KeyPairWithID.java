package io.zbhavyai.model;

import java.security.KeyPair;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyPairWithID(
        @JsonProperty("keyID") String keyID,
        @JsonProperty("keyPair") KeyPair keyPair) {

    @JsonProperty("keyID")
    public String getKeyID() {
        return this.keyID;
    }

    @JsonProperty("keyPair")
    public KeyPair getKeyPair() {
        return this.keyPair;
    }
}
