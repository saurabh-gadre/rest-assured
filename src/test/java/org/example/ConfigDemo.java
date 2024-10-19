package org.example;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ConfigDemo {

    private static final String BASE_URL = "https://api.github.com/";

    @Test
    public void redirectConfigTest(){
        RestAssured.config = RestAssured.config()
                        .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(2));
        
        RestAssured
                .get(BASE_URL+"repos/twitter/bootstrap")
                .then()
                .statusCode(200);
    }

    @Test
    public void failureConfigTest(){
        ResponseValidationFailureListener responseValidationFailureListener = (reqSpec, resSpec, response) ->{
            System.out.printf("Failure Encountered, response status was %s, and the body contained - %s", response.statusCode(), response.body().asPrettyString());
        };

        RestAssured.config = RestAssured.config()
                        .failureConfig(FailureConfig.failureConfig().failureListeners(responseValidationFailureListener));

        RestAssured
                .get(BASE_URL+"users/rest-assured")
                .then()
                .body("some.path", Matchers.equalTo("Some Thing"));
    }
}
