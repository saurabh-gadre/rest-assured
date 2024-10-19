package org.example.objectmapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;
import org.example.ConfigFactory;
import org.example.User2;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static org.testng.AssertJUnit.assertEquals;

public class ObjectMappingDemo2 {

    private static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @BeforeTest
    public void init() {
        RestAssured.config = ConfigFactory.getDefaultConfig();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @AfterTest
    public void clear(){
        RestAssured.config = null;
        RestAssured.responseSpecification = null;
    }

    @Test
    public void objectMappingTestWithSpecificMapper() {

        User2 user2 = RestAssured
                .given()
                .log().all()
                .spec(getRequestSpecification())
                .when()
                .log().all()
                .get()
                .as(User2.class);

        assertEquals(user2.getId(), 19369327);
        assertEquals(user2.getLogin(), "rest-assured");
    }

    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
}
