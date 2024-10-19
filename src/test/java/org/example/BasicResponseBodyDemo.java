package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class BasicResponseBodyDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void basicResponseBodyTest(){
        Response response = RestAssured.get(BASE_URL+ "rate_limit");
        ResponseBody<?> responseBody = response.body();

        JsonPath jsonPath = responseBody.jsonPath();

        Map<String,String> fullJson = jsonPath.get();
        System.out.println("Full Json: "+ fullJson);

        Map<String,String> searchJson = jsonPath.getMap("resources.search");
        System.out.println("Search Json: "+ searchJson);

        System.out.println("integration_manifest remaining - "+ jsonPath.get("resources.integration_manifest.remaining"));
        System.out.println("rate used - "+ jsonPath.get("rate.used"));
    }

    @DataProvider(name = "getNames")
    public Object[][] getNames(){
        return new Object[][]{
                new String[]{"kevinclark"},
                new String[]{"brynary"}
        };
    }

    @Test(dataProvider = "getNames")
    public void matcherResponseTest(String name){
        Response response = RestAssured.get(BASE_URL+"users/"+name);
        response
                .then()
                .body("url", Matchers.containsString(name))
                .body("url", Matchers.containsString(response.body().jsonPath().getString("login")));
    }
}
