package com.qa.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.utils.ApiUtils;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class UserApiDataProviderTest extends BaseTest {

    @DataProvider(name = "userIds")
    public Object[][] userIds() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("user-ids.csv");
        if (input == null) {
            throw new IllegalStateException("Could not find user-ids.csv");
        }

        List<Object[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(new Object[] { line.trim() });
                }
            }
        }

        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "userIds")
    public void verifyGetUser(String userId) {
        Response response = ApiUtils.get(
                "https://jsonplaceholder.typicode.com/users/" + userId,
                requestSpec);

        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.jsonPath().get("id"));
        assertEquals(response.jsonPath().getInt("id"), Integer.parseInt(userId));
        assertFalse(response.jsonPath().getString("name").isEmpty());
        assertFalse(response.jsonPath().getString("email").isEmpty());
    }
}
