
package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pojo.Booking;
import com.qa.pojo.BookingDates;
import com.qa.utils.ApiUtils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CreateBookingTest extends BaseTest {

    
    public void createBooking() {

        // Create booking dates object
        BookingDates dates = new BookingDates();

        dates.setCheckin("2026-11-13");
        dates.setCheckout("2026-11-15");

        // Create booking object
        Booking booking = new Booking();

        booking.setFirstname("John");
        booking.setLastname("Smith");
        booking.setTotalprice(500);
        booking.setDepositpaid(true);
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Breakfast");

        /*  Send POST request
        given()
            .spec(requestSpec)
            .body(booking)

        .when()
            .post("/booking")

        .then()
            .statusCode(200)
            .contentType("application/json");*/


             Response response = ApiUtils.post(
                "/booking", booking, requestSpec);

        // Validate response
        assertEquals(response.getStatusCode(), 200);

        int bookingId = response.jsonPath().getInt("bookingid");

        System.out.println("Created Booking ID: " + bookingId);
    }
}
