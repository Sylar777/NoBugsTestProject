package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends BaseTest {
    @Test
    void getDataTest() {
        var response = given()
                .get("100")
                .then()
                .statusCode(200)
                .log().all()
                .extract().body().jsonPath().get("id");

        System.out.println(response);
    }

    @Test
    void createDataTest() {
        var body = "{\n" +
                "    \"userId\": 101,\n" +
                "    \"id\": 101,\n" +
                "    \"title\": \"title\"\n" +
                "    \"body\": \"Test Body\"\n" +
                "}";

        var response = given()
                .post(body)
                .then()
                .statusCode(201)
                .log().all()
                .extract().body().jsonPath().get("id");

        System.out.println(response);
    }

    @Test
    void updateDataTest() {
        var body = "{\n" +
                "    \"userId\": 101,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"title\"\n" +
                "    \"body\": \"Test Body\"\n" +
                "}";

        var response = given()
                .post(body)
                .then()
                .statusCode(201)
                .log().all()
                .extract().body().jsonPath().get("id");

        System.out.println(response);

    }

    @Test
    void deleteDataTest() {
        var response = given()
                .delete("100")
                .then()
                .statusCode(200)
                .log().all()
                .extract().body().jsonPath().get("id");

        System.out.println(response);

        var getResponse = given()
                .get("100")
                .then()
                .statusCode(200)
                .log().all()
                .extract().body().jsonPath().get("id");

        System.out.println(getResponse);

    }
}
