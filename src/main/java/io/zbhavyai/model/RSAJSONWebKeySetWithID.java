package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RSAJSONWebKeySetWithID(
        @JsonProperty("clientID") String clientID,
        @JsonProperty("rsaJsonWebKeySet") RSAJSONWebKeySet rsaJsonWebKeySet) {
}
