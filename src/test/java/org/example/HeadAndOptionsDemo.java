package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class HeadAndOptionsDemo {

    private static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void headMethodTest(){
        RestAssured.head(BASE_URL)
                .then()
                .statusCode(200)
                .body(Matchers.emptyOrNullString());
    }

    @Test
    public void optionsMethodTest(){
        Response response = RestAssured
                .options(BASE_URL);

        response
                .then()
                .statusCode(204)
                .header("access-control-allow-methods",Matchers.equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(Matchers.emptyOrNullString());

    }
}
