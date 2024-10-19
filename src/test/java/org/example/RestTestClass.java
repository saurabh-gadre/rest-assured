package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class RestTestClass {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void testGithubAPI() {
        RestAssured.when().get(BASE_URL).then().statusCode(200);
    }

    @Test
    public void testPeek() {
        RestAssured.when().get(BASE_URL).peek(); // unformatted header & response body
    }

    @Test
    public void testPrettyPeek() {
        RestAssured.when().get(BASE_URL).prettyPeek(); // formatted header & response body
    }

    @Test
    public void testPrint() {
        RestAssured.when().get(BASE_URL).print(); // unformatted response body
    }

    @Test
    public void testPrettyPrint() {
        RestAssured.when().get(BASE_URL).prettyPrint(); // formatted response body
    }
}
