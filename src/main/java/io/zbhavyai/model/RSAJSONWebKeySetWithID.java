package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RSAJSONWebKeySetWithID(
        @JsonProperty("clientID") String clientID,
        @JsonProperty("rsaJsonWebKeySet") RSAJSONWebKeySet rsaJsonWebKeySet) {

    @JsonProperty("clientID")
    public String getClientID() {
        return this.clientID;
    }

    @JsonProperty("rsaJsonWebKeySet")
    public RSAJSONWebKeySet getRSAJSONWebKeySet() {
        return this.rsaJsonWebKeySet;
    }
}
