package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyPairAndCertificateInputDTO(
        @JsonProperty("keyID") String keyID,
        @JsonProperty("publicKey") String publicKey,
        @JsonProperty("privateKey") String privateKey,
        @JsonProperty("issuer") String issuer,
        @JsonProperty("subject") String subject) {
}
