package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableRepeatingRespItemDemo {

    public static final String BASE_URL = "https://reqres.in/api";
    public static final String BASE_PATH= "/users?page=1";

    @BeforeTest
    public void init(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.rootPath = "data";
    }

    @Test
    public void repeatingItemsTest(){
        Response response = RestAssured.get();

        response
                .then()
                .body("id[0]", equalTo(1))
                .body("id[1]", equalTo(2))
                .body("first_name[1]",equalTo("Janet"))
                .body("last_name[1]",equalTo("Weaver"))
                .body("first_name",hasItem("George"))
                .body("first_name",hasItems("Janet","Emma","Charles","Tracey"))
                .body("id", hasSize(6));
    }
}
