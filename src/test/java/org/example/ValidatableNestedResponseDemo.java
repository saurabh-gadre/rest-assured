package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ValidatableNestedResponseDemo {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void nestedResponseTest(){
        Response response = RestAssured.get(BASE_URL+"rate_limit");

        response
                .then()
                .rootPath("resources.core")
                    .body("limit",equalTo(60))
                    .body("remaining",equalTo(58))
                    .body("reset",notNullValue())
                .rootPath("resources.search")
                    .body("limit",equalTo(10))
                    .body("remaining",equalTo(10))
                    .body("reset",notNullValue())
                .noRootPath()
                .body("rate.remaining",equalTo(58));
    }
}
