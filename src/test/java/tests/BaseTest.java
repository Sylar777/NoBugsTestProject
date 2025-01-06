package tests;

import io.restassured.RestAssured;
import org.example.requetApiSteps.RequestApiSteps;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    RequestApiSteps requestApiSteps;


    public BaseTest() {
        requestApiSteps = new RequestApiSteps();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
    }
}
