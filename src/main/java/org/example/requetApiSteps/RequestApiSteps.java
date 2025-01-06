package org.example.requetApiSteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.example.specs.SpecRestApi.defaultLoggingRequestSpec;

public class RequestApiSteps {
    @Step("Get data")
    public Response getData(String id) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .get(id)
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
