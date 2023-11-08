package io.zbhavyai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RSAJSONWebKeySetWithID {

    private final String clientID;

    private final RSAJSONWebKeySet rsaJsonWebKeySet;

    @JsonCreator
    public RSAJSONWebKeySetWithID(
            @JsonProperty("clientID") final String clientID,
            @JsonProperty("rsaJsonWebKeySet") final RSAJSONWebKeySet rsaJsonWebKeySet) {
        this.clientID = clientID;
        this.rsaJsonWebKeySet = rsaJsonWebKeySet;
    }

    
}
