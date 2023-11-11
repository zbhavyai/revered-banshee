package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyPairDTO(
        @JsonProperty("keyID") String keyID,
        @JsonProperty("publicKey") String publicKey,
        @JsonProperty("privateKey") String privateKey) {
}
