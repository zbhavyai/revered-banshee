package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RSAJSONWebKey(
        @JsonProperty("kid") String keyID,
        @JsonProperty("kty") String keyType,
        @JsonProperty("n") String modulus,
        @JsonProperty("e") String exponent) {

    @JsonProperty("kid")
    public String getKeyID() {
        return this.keyID;
    }

    @JsonProperty("kty")
    public String getKeyType() {
        return this.keyType;
    }

    @JsonProperty("n")
    public String getModulus() {
        return this.modulus;
    }

    @JsonProperty("e")
    public String getExponent() {
        return this.exponent;
    }
}
