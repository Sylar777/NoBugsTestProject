package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.example.models.CrudModel;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;

public class CrudTests extends BaseTest {
    private CrudModel requestBody;
    private String jsonBody;

    @Test
    void getDataTest() {
        step("Get list of data and preparing body for JSON request", () -> {
            int randomUserId = Integer.parseInt(faker.number().digits(8));
            int randomId = faker.number().numberBetween(1, 100);
            String randomTitle = faker.book().title();
            String randomBody = faker.lorem().sentence(10);

            requestBody = prepareTestDataRequest(randomUserId, randomId, randomTitle, randomBody);
        });

       step("Get data by id and compare ids", () -> {
            var response = requestApiSteps.getData(requestBody.getId());

            Assertions.assertEquals((Integer) response.body().jsonPath().get("id"), requestBody.getId());
        });
    }

    @Test
    void createDataTest() {
        step("Get list of data and preparing body for JSON request", () -> {
            findMaxId();

            int randomUserId = Integer.parseInt(faker.number().digits(8));
            // TODO: verified that randomUserId will be unique on the server side
            int randomId = maxId + 1;
            String randomTitle = faker.book().title();
            String randomBody = faker.lorem().sentence(10);

            requestBody = prepareTestDataRequest(randomUserId, randomId, randomTitle, randomBody);
        });

        step("Create new record on Server side by post request", () -> {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBody = objectMapper.writeValueAsString(requestBody);

            requestApiSteps.createData(jsonBody);
        });

        step("Get new record from Server side by Id and compare ids", () -> {
            var createdNewRecordResponse = requestApiSteps.getData(requestBody.getId());

            Assertions.assertEquals((Integer) createdNewRecordResponse.body().jsonPath().get("id"), requestBody.getId());
        });

        // + get all records from Server side
        // + we need some method for sorting data in the list
        // + check which the max Id value we have on Server side
        // + prepare body for request with Id = maxId + 1
        // + sending request
        // + Assertion: check that record with this Id exist on Server side
    }

    @Test
    void updateDataTest() {
        step("Prepare test data on Server side", () -> {
            step("Get list of data and preparing body for JSON request", () -> {
                findMaxId();

                int randomUserId = Integer.parseInt(faker.number().digits(8));
                // TODO: verified that randomUserId will be unique on the server side
                int randomId = maxId + 1;
                String randomTitle = faker.book().title();
                String randomBody = faker.lorem().sentence(10);

                requestBody = prepareTestDataRequest(randomUserId, randomId, randomTitle, randomBody);
            });

            step("Create new record on Server side by post request", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonBody = objectMapper.writeValueAsString(requestBody);

                requestApiSteps.createData(jsonBody);
            });
        });

        step("Prepare JSON body for update request", () -> {
            int randomUserId = Integer.parseInt(faker.number().digits(8));
            // TODO: verified that randomUserId will be unique on the server side
            int randomId = maxId + 1;
            String randomTitle = "UPDATED " + faker.book().title();
            String randomBody = "UPDATED " + faker.lorem().sentence(10);

            requestBody = prepareTestDataRequest(randomUserId, randomId, randomTitle, randomBody);
        });

        step("Update Update the existing record on Server side by post request and verify that data on Server was updated", () -> {
            step("Update the existing record on Server side by post request", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonBody = objectMapper.writeValueAsString(requestBody);

                requestApiSteps.updateData(jsonBody);
            });

            step("Get updated record from Server side by Id and compare ids", () -> {
                var updatedRecordResponse = requestApiSteps.getData(requestBody.getId());

                Assertions.assertTrue(updatedRecordResponse.body().jsonPath().get("title").toString().contains("UPDATED"));
                Assertions.assertTrue(updatedRecordResponse.body().jsonPath().get("body").toString().contains("UPDATED"));
            });
        });

        // + step: prepare data for testing goals on server side
        // + step: preparing update body for the request
        // + step: sending request
        // + step: getting updated data from the server side
    }

    @Test
    void deleteDataTest() {
        step("Prepare test data on Server side", () -> {
            step("Get list of data and preparing body for JSON request", () -> {
                findMaxId();

                int randomUserId = Integer.parseInt(faker.number().digits(8));
                // TODO: verified that randomUserId will be unique on the server side
                int randomId = maxId + 1;
                String randomTitle = faker.book().title();
                String randomBody = faker.lorem().sentence(10);

                requestBody = prepareTestDataRequest(randomUserId, randomId, randomTitle, randomBody);
            });

            step("Create new record on Server side by post request", () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                jsonBody = objectMapper.writeValueAsString(requestBody);

                requestApiSteps.createData(jsonBody);
            });
        });

        step("Delete the existing record on Server side by Id and verify that data was deleted", () -> {
            step("Send request to delete data by Id on the Server side", () -> {
                requestApiSteps.deleteData(String.valueOf(requestBody.getId()));
            });

            step("Get deleted record from Server side by Id and verify that data was deleted", () -> {
                var deletedRecordResponse = requestApiSteps.getData(requestBody.getId());

                Assertions.assertEquals(deletedRecordResponse.statusCode(), 404);
            });
        });

        // + step: prepare data for testing goals on server side
        // + step: sending request for data creation
        // + step: sending request to delete data on server side
        // + step: getting data from the server side by Id to verify that data was deleted
    }
}
