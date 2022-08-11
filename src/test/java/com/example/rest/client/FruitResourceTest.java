package com.example.rest.client;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.jupiter.api.Test;

import com.example.rest.client.resource.WireMockExtensions;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class FruitResourceTest {
	
	@Test
    public void testFruitsEndpoint() {
        given()
            .when().get("/fruits")
            .then()
            .statusCode(200)
            .body("$.size()", is(2),
                "[0].name", is("apple"),
                "[0].description", is("apple"),
                "[1].name", is("orange"),
                "[1].description", is("orange"));
    }

}
