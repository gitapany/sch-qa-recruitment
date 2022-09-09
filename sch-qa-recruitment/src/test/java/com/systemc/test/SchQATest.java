package com.systemc.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.UUID;

public class SchQATest {

    @Test(description = "Post Method Test")
    public void testPostMethod() {
        String name = UUID.randomUUID().toString();
        // Create POST request data
        HashMap data = new HashMap<>();
        data.put("name", name);
        data.put("description", "Test Auto desc");
        data.put("price", 500);
        data.put("itemCount", 2);
        data.put("active", "true");
        given()
                .baseUri("https://schqarecruitment.azurewebsites.net")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(data)
                .when()
                .post("/v1/product")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(name));

    }

    @Test(description = "Post Method with name attribute more than 50 characters")
    public void testPostNameMoreThanFiftyChars() {
        String name = "Test Automation 3 whfhowfoiw98347839hfnsknnaslkjsdfhhasl";
        // Create POST request data
        HashMap data = new HashMap<>();
        data.put("name", name);
        data.put("description", "Test Auto desc");
        data.put("price", 500);
        data.put("itemCount", 2);
        data.put("active", "true");
        given()
                .baseUri("https://schqarecruitment.azurewebsites.net")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(data)
                .when()
                .post("/v1/product")
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("error.code", equalTo("2"),
                        "error.message", equalTo("Name cannot be longer than 50 characters"));

    }

}
