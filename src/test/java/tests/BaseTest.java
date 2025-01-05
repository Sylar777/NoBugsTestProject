package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
    }
}
