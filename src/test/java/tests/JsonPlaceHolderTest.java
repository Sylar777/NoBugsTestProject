package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.example.models.CrudModel;
import org.junit.jupiter.api.*;

public class JsonPlaceHolderTest extends BaseTest {
    private static final Faker faker = new Faker();
    private String jsonBody;
    private CrudModel requestBody;

    @BeforeEach
    void beforeEach() throws JsonProcessingException {
        String randomUserId = "" + faker.number().numberBetween(1, 100);
        String randomId = "" + faker.number().numberBetween(1, 100);
        String randomTitle = faker.book().title();
        String randomBody = faker.lorem().sentence(10);

        requestBody = CrudModel.builder()
                .userId(randomUserId)
                .id(randomId)
                .title(randomTitle)
                .body(randomBody)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        jsonBody = objectMapper.writeValueAsString(requestBody);
    }

    @Test
    void getDataTest() {
        var response = requestApiSteps.getData(requestBody.getId());

        Assertions.assertEquals(response.body().jsonPath().get("id").toString(), requestBody.getId());
    }

    @Test
    void createDataTest() {
        var response =  requestApiSteps.createData(jsonBody);

        // Assertions
    }

    @Test
    void updateDataTest() {
        var response = requestApiSteps.updateData(jsonBody);

        // Assertions
    }

    @Test
    void deleteDataTest() {
        var response = requestApiSteps.deleteData(requestBody.getId());

        // Assertions
    }
}
