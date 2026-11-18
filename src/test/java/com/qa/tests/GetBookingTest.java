
package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.utils.ApiUtils;
import static org.testng.Assert.*;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetBookingTest extends BaseTest {

 
    public void getAllBookings() {

               Response response = ApiUtils.get(
                "/booking", requestSpec);

        assertEquals(response.getStatusCode(), 200);

        System.out.println(response.asString());
    }
}
