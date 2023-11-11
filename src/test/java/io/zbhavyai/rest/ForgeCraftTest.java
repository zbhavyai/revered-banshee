package io.zbhavyai.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ForgeCraftTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/api/v1/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from the server!"));
    }
}
