package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.utils.ApiUtils;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class UserApiTest extends BaseTest {

    @Test
    public void verifyGetUser() {
        Response response = ApiUtils.get(
                "https://jsonplaceholder.typicode.com/users/1",
                requestSpec);

        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.jsonPath().get("id"));
        assertEquals(response.jsonPath().getInt("id"), 1);
        assertFalse(response.jsonPath().getString("name").isEmpty());
        assertFalse(response.jsonPath().getString("email").isEmpty());
    }
}
