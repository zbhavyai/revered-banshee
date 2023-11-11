package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CertificateInputDTO(
        @JsonProperty("issuer") String issuer,
        @JsonProperty("subject") String subject) {
}
