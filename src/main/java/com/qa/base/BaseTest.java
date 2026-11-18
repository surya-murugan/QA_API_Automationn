package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;
    protected String username;
    protected String password;

    @BeforeClass
    public void setup() throws IOException {

        Properties prop = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties");

        prop.load(file);
        file.close();

        String baseUrl = prop.getProperty("baseUrl");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
