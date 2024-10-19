package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class OtherMethodsDemo {

    private static final String BASE_URL = "https://fakestoreapi.com";

    @Test
    public void addProduct() {
        Response response = RestAssured
                .given()
                .header("Authorization","KLCSD_weqqw_SA233545ddrwer_qr23233")
                .body("{'title': 'test product','price': 13.5,'description': 'lorem ipsum set','image': 'https://i.pravatar.cc','category': 'electronic'}")
                .when()
                .post(BASE_URL + "/products");

        response.then()
                .statusCode(200);
        System.out.println(response.body().prettyPeek());
    }

    @Test
    public void updateProductUsingPATCH() {
        Response response = RestAssured
                .given()
                .header("Authorization","KLCSD_weqqw_SA233545ddrwer_qr23233")
                .body("{'title': 'test electronic product','price': 20.5}")
                .when()
                .patch(BASE_URL + "/products/7");

        response.then()
                .statusCode(200);
        System.out.println(response.body().prettyPeek());
    }

    @Test
    public void updateProductUsingPUT() {
        Response response = RestAssured
                .given()
                .header("Authorization","KLCSD_weqqw_SA233545ddrwer_qr23233")
                .body("{'title': 'test electronic product','price': 13.5,'description': 'lorem ipsum set','image': 'https://i.pravatar.cc','category': 'electronic'}")
                .when()
                .put(BASE_URL + "/products/7");

        response.then()
                .statusCode(200);
        System.out.println(response.body().prettyPeek());
    }

    @Test
    public void deleteProduct() {
        Response response = RestAssured
                .given()
                .header("Authorization","KLCSD_weqqw_SA233545ddrwer_qr23233")
                .when()
                .delete(BASE_URL + "/products/6");

        response.then()
                .statusCode(200);
    }

    @Test
    public void traceRequestTest() {
        Response response = RestAssured
                .given()
                .header("Authorization","KLCSD_weqqw_SA233545ddrwer_qr23233")
                .when()
                .request(Method.TRACE, BASE_URL + "/products/6");

        response.then()
                .statusCode(405);
    }
}

