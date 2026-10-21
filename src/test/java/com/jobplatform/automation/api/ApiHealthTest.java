package com.jobplatform.automation.api;

import com.jobplatform.automation.config.ConfigReader;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ApiHealthTest {
    @Test
    void healthEndpointRespondsSuccessfully() {
        String baseUrl = ConfigReader.require("api.base.url");
        String healthPath = ConfigReader.get("api.health.path", "/health");

        given()
            .baseUri(baseUrl)
        .when()
            .get(healthPath)
        .then()
            .statusCode(200);
    }
}
