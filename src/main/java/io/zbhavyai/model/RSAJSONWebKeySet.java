package io.zbhavyai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RSAJSONWebKeySet(
        @JsonProperty("keys") List<RSAJSONWebKey> keys) {
}
