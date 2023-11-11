package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RSAJSONWebKey(
        @JsonProperty("kid") String keyID,
        @JsonProperty("kty") String keyType,
        @JsonProperty("n") String modulus,
        @JsonProperty("e") String exponent) {
}
