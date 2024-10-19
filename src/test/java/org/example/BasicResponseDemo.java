package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicResponseDemo {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void convenienceMethod(){
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getStatusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void genericHeaders(){
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getHeader("server"), "github.com");
        assertEquals(Integer.valueOf(response.getHeader("x-ratelimit-limit")),60);
    }

    @Test
    public void getResponseHeaders(){
        Response response = RestAssured.get(BASE_URL);
        Headers headers = response.getHeaders();
        System.out.println("Headers: "+ headers);

        boolean checkForGithubId = headers.hasHeaderWithName("X-GitHub-Request-Id");
        assertTrue(checkForGithubId);
    }

    @Test
    public void getTime(){
        Response response = RestAssured.get(BASE_URL);
        long resTime = response.getTime();
        System.out.println("Response Time in MilliSeconds: "+ resTime);
        System.out.println("Response Time in Seconds: "+ response.getTimeIn(TimeUnit.SECONDS));
    }
}
