package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record JSONWebKey(String keyID, String keyType, String modulus, String exponent) {

    @JsonCreator
    public JSONWebKey(
            @JsonProperty("kid") final String keyID,
            @JsonProperty("kty") final String keyType,
            @JsonProperty("n") final String modulus,
            @JsonProperty("e") final String exponent) {
        this.keyID = keyID;
        this.keyType = keyType;
        this.modulus = modulus;
        this.exponent = exponent;
    }

    @Override
    @JsonProperty("kid")
    public String keyID() {
        return this.keyID;
    }

    @Override
    @JsonProperty("kty")
    public String keyType() {
        return this.keyType;
    }

    @Override
    @JsonProperty("n")
    public String modulus() {
        return this.modulus;
    }

    @Override
    @JsonProperty("e")
    public String exponent() {
        return this.exponent;
    }
}
