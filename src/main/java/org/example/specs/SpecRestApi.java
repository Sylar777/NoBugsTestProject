package org.example.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.example.helpers.CustomApiListener.withCustomTemplates;

public class SpecRestApi {
    public static final RequestSpecification defaultLoggingRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);
}
