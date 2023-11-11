package io.zbhavyai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CertificateDTO(
        @JsonProperty("encoded") String encoded,
        @JsonProperty("prettyFormat") String prettyFormat) {
}
