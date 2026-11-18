
package com.qa.utils;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class AuthUtil {

    public static String generateToken(
            RequestSpecification requestSpec,
            String username,
            String password) {

        String requestBody = "{"
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        String token =
                given()
                    .spec(requestSpec)
                    .body(requestBody)

                .when()
                    .post("/auth")

                .then()
                    .statusCode(200)
                    .extract()
                    .path("token");

        return token;
    }
}
