package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.KeyPair;

public record KeyPairWithID(String keyID, KeyPair keyPair) {

    @JsonCreator
    public KeyPairWithID(
            @JsonProperty("keyID") String keyID,
            @JsonProperty("keyPair") KeyPair keyPair) {
        this.keyID = keyID;
        this.keyPair = keyPair;
    }

    @JsonProperty("keyID")
    public String getKeyID() {
        return this.keyID;
    }

    @JsonProperty("keyPair")
    public KeyPair getKeyPair() {
        return this.keyPair;
    }
}
