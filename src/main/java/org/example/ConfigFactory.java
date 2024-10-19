package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.*;
import io.restassured.filter.log.LogDetail;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ConfigFactory {

    public static RestAssuredConfig getDefaultConfig(){
        ResponseValidationFailureListener responseValidationFailureListener = (reqSpec, resSpec, response) ->{
            System.out.printf("Failure Encountered, response status was %s, and the body contained - %s", response.statusCode(), response.body().asPrettyString());
        };

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()))
                .failureConfig(FailureConfig.failureConfig().failureListeners(responseValidationFailureListener))
                .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(2))
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));

        return RestAssured.config;
    }

    private static Jackson2ObjectMapperFactory getDefaultMapper(){
        return  (type, s) -> {
            return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        };
    }
}
