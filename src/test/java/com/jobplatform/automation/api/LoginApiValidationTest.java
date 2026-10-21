package com.jobplatform.automation.api;

import com.jobplatform.automation.config.ConfigReader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class LoginApiValidationTest {
    @Test
    void loginRejectsInvalidCredentials() {
        String baseUrl = ConfigReader.require("api.base.url");
        String loginPath = ConfigReader.get("api.login.path", "/api/v1/login");

        given()
            .baseUri(baseUrl)
            .contentType("application/json")
            .body(Map.of(
                "email", "selenium.invalid.user@example.invalid",
                "password", "DefinitelyWrongPassword123!"
            ))
        .when()
            .post(loginPath)
        .then()
            .statusCode(anyOf(is(400), is(401), is(403), is(422)));
    }

    @Test
    void loginRejectsMalformedEmailOrMissingFields() {
        String baseUrl = ConfigReader.require("api.base.url");
        String loginPath = ConfigReader.get("api.login.path", "/api/v1/login");

        given()
            .baseUri(baseUrl)
            .contentType("application/json")
            .body(Map.of("email", "not-an-email", "password", "x"))
        .when()
            .post(loginPath)
        .then()
            .statusCode(anyOf(is(400), is(401), is(422)));
    }
}
