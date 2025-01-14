package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.CrudModel;
import org.example.requetApiSteps.RequestApiSteps;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static final Faker faker = new Faker();
    protected CrudModel requestBody;
    protected RequestApiSteps requestApiSteps;
    protected int maxId;


    public BaseTest() {
        requestApiSteps = new RequestApiSteps();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
    }

    public int getMaxId(Response response) {
        // Find the max id value in the response
        return 0;
    }

    public CrudModel prepareTestDataRequest(int randomUserId, int randomId, String randomTitle, String randomBody) throws JsonProcessingException {
        return requestBody = CrudModel.builder()
                .userId(randomUserId)
                .id(randomId)
                .title(randomTitle)
                .body(randomBody)
                .build();
    }

    public int findMaxId() {
        var response = requestApiSteps.getData();

        // check if response has empty body (no records exist on server side) then return maxId = 0

        return maxId = getMaxId(response);
    }
}
