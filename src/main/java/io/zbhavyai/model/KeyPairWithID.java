package io.zbhavyai.model;

import java.security.KeyPair;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyPairWithID(
        @JsonProperty("keyID") String keyID,
        @JsonProperty("keyPair") KeyPair keyPair) {
}
