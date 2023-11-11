package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyPairAndCertificateDTO(
        @JsonProperty("keyID") String keyID,
        @JsonProperty("publicKey") String publicKey,
        @JsonProperty("privateKey") String privateKey,
        @JsonProperty("certificate") String certificate,
        @JsonProperty("certificatePretty") String certificatePretty) {
}
