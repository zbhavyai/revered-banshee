package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RSAJSONWebKeySet {

    private final List<RSAJSONWebKey> keys = new ArrayList<>();

    @JsonCreator
    public RSAJSONWebKeySet(@JsonProperty("keys") final List<RSAJSONWebKey> keys) {
        this.keys.addAll(keys);
    }

    public RSAJSONWebKeySet(final RSAJSONWebKey key) {
        this.keys.add(key);
    }

    @JsonProperty("keys")
    public List<RSAJSONWebKey> getKeyList() {
        return this.keys;
    }
}
