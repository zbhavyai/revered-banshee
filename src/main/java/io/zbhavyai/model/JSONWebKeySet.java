package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class JSONWebKeySet {

    private final List<JSONWebKey> keys = new ArrayList<>();

    @JsonCreator
    public JSONWebKeySet(@JsonProperty("keys") final List<JSONWebKey> keys) {
        this.keys.addAll(keys);
    }

    public JSONWebKeySet(final JSONWebKey key) {
        this.keys.add(key);
    }

    @JsonProperty("keys")
    public List<JSONWebKey> getKeyList() {
        return this.keys;
    }
}
