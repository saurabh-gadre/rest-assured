package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ValidatableResponseDemo {

    public static final String BASE_URL = "https://api.github.com/";
    Map<String, String> expectedHeaders = Map.of("content-encoding","gzip",
                                             "content-security-policy","default-src 'none'",
                                             "content-type","application/json; charset=utf-8",
                                             "server","github.com");

    
    @Test
    public void basicValidatableTest(){
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .assertThat()
                .header("x-github-media-type","github.v3; format=json");
    }

    @Test
    public void hamcrestTest(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(300))
                .header("server",Matchers.containsStringIgnoringCase("github.com"))
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
                .header("x-ratelimit-limit", Matchers.not(Matchers.emptyString()));
    }

    @Test
    public void advancedHamcrestTest(){
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.lessThan(100))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("date", x->LocalDate.parse(x,DateTimeFormatter.RFC_1123_DATE_TIME), Matchers.equalTo(LocalDate.now()))
                .header("x-ratelimit-limit", response -> Matchers.greaterThan(response.header("x-ratelimit-remaining")))
                .headers(expectedHeaders);
    }

}
