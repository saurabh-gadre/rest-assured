package org.example.objectmapping;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.example.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ObjectMappingDemo {

    private static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingTest() {
        User user = RestAssured
                .given()
                .get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);

        Assert.assertEquals(user.getLogin(),"rest-assured");
        Assert.assertEquals(user.getId(),19369327);
        Assert.assertEquals(user.getPublicRepos(),2);
        Assert.assertEquals(user.getUserViewType(),"public");
    }
}
