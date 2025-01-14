package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class DataNegativeScenariosTests extends BaseTest {
    private String jsonBody;
    private String newJsonBodyWithTheSameUserId;
    private int randomUserId;
    private int id;

    @Test
    void userIdDuplicateTest() {
        step("Prepare test data on the server side", () -> {
            step("Get list of data and preparing body for JSON request", () -> {
                findMaxId();

                randomUserId = Integer.parseInt(faker.number().digits(8));
                // TODO: verified that randomUserId will be unique on the server side
                id = maxId + 1;
                String randomTitle = faker.book().title();
                String randomBody = faker.lorem().sentence(10);

                requestBody = prepareTestDataRequest(randomUserId, id, randomTitle, randomBody);
            });

            step("Create new record on Server side by post request", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonBody = objectMapper.writeValueAsString(requestBody);

                requestApiSteps.createData(jsonBody);
            });
        });

        step("Prepare test data with the same userId", () -> {
            id = maxId + 1;
            String randomTitle = faker.book().title();
            String randomBody = faker.lorem().sentence(10);

            requestBody = prepareTestDataRequest(randomUserId, id, randomTitle, randomBody);
        });

        step("Create new record on Server side by post request", () -> {
            ObjectMapper objectMapper = new ObjectMapper();
            newJsonBodyWithTheSameUserId = objectMapper.writeValueAsString(requestBody);

            requestApiSteps.createDataWithDuplicateValue(newJsonBodyWithTheSameUserId);
        });

        // + step: prepare test data with some userId on the server side
        // + step: prepare test data with the same userId
        // + step: send new request for data creation with the same userId
        // + step: check that the response status code is 400
    }

    @Test
    void idDuplicateTest() {
        step("Prepare test data on the server side", () -> {
            step("Get list of data and preparing body for JSON request", () -> {
                findMaxId();

                randomUserId = Integer.parseInt(faker.number().digits(8));
                // TODO: verified that randomUserId will be unique on the server side
                id = maxId + 1;
                String randomTitle = faker.book().title();
                String randomBody = faker.lorem().sentence(10);

                requestBody = prepareTestDataRequest(randomUserId, id, randomTitle, randomBody);
            });

            step("Create new record on Server side by post request", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonBody = objectMapper.writeValueAsString(requestBody);

                requestApiSteps.createData(jsonBody);
            });
        });

        step("Prepare test data with the same id", () -> {
            randomUserId = Integer.parseInt(faker.number().digits(8));
            // TODO: verified that randomUserId will be unique on the server side
            String randomTitle = faker.book().title();
            String randomBody = faker.lorem().sentence(10);

            requestBody = prepareTestDataRequest(randomUserId, id, randomTitle, randomBody);
        });

        step("Create new record on Server side by post request", () -> {
            ObjectMapper objectMapper = new ObjectMapper();
            newJsonBodyWithTheSameUserId = objectMapper.writeValueAsString(requestBody);

            requestApiSteps.createDataWithDuplicateValue(newJsonBodyWithTheSameUserId);
        });

        // + step: prepare test data with some id on the server side
        // + step: prepare test data with the same id
        // + step: send new request for data creation with the same id
        // + step: check that the response status code is 400
    }
}
