package com.ctseducare.moviebook;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class StatusResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/status")
          .then()
             .statusCode(200)
             .body(is("I am UP!"));
    }

}