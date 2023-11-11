package io.zbhavyai.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.zbhavyai.service.ForgeCraft;
import io.zbhavyai.service.ForgeCraftImpl;

class TestRSAJSONWebKeySetWithID {

    private static final ObjectMapper mapper = (new ObjectMapper()).registerModule(new JavaTimeModule());

    @Test
    void testSerialization() {
        RSAJSONWebKeySetWithID rsaJWKS = createJWKS();
        String serializedJWKS = serialize(rsaJWKS);
        RSAJSONWebKeySetWithID deserializedJWKS = deserialize(serializedJWKS, RSAJSONWebKeySetWithID.class);
        assertEquals(rsaJWKS, deserializedJWKS);
    }

    private RSAJSONWebKeySetWithID createJWKS() {
        ForgeCraft fc = new ForgeCraftImpl();
        return fc.generateJSONWebKeySet(fc.generateKeyPair("testKeyID"), "testClientID");
    }

    private <T> String serialize(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T deserialize(String value, Class<T> clazz) {
        try {
            return mapper.readValue(value, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
