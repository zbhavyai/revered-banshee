package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CertificateInput(
        @JsonProperty("issuer") String issuer,
        @JsonProperty("subject") String subject) {
}
