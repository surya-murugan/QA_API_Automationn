
package com.qa.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class ApiUtils {

    // GET request
    public static Response get(String endpoint,
            RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
            .when()
                .get(endpoint);
    }

    // POST request
    public static Response post(String endpoint, Object body,
            RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
                .body(body)
            .when()
                .post(endpoint);
    }

    // PUT request
    public static Response put(String endpoint, Object body,
            String token, RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
                .cookie("token", token)
                .body(body)
            .when()
                .put(endpoint);
    }

    // PATCH request
    public static Response patch(String endpoint, Object body,
            String token, RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
                .cookie("token", token)
                .body(body)
            .when()
                .patch(endpoint);
    }

    // DELETE request
    public static Response delete(String endpoint,
            String token, RequestSpecification requestSpec) {

        return given()
                .spec(requestSpec)
                .cookie("token", token)
            .when()
                .delete(endpoint);
    }
}
