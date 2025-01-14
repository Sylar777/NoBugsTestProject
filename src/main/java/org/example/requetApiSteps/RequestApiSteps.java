package org.example.requetApiSteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.example.specs.SpecRestApi.defaultLoggingRequestSpec;

public class RequestApiSteps {
    @Step("Get list of data")
    public Response getData() {
        return given()
                .spec(defaultLoggingRequestSpec)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Get data by id")
    public Response getData(int id) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .get(String.valueOf(id))
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Create data")
    public Response createData(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .post(jsonBody)
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .response();
    }

    @Step("Create data with expecting status code 400")
    public Response createDataWithDuplicateValue(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .post(jsonBody)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .response();
    }

    @Step("Update data")
    public Response updateData(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .post(jsonBody)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Delete data")
    public Response deleteData(String id) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .delete(id)
                .then()
                .statusCode(SC_NO_CONTENT)
                .extract()
                .response();
    }
}
