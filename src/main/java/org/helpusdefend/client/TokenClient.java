package org.helpusdefend.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.helpusdefend.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class TokenClient extends AccessTokenGenerationClient {

    @Step
    @Description("Base url Create token POST {url}/token/")
    public Response createToken(User user) {
       return executeRequest("POST", user);
    }

    @Step
    @Description("Base url Check token PUT {url}/token/")
    public Response checkToken(User user) {
        return executeRequest("PUT", user);
    }

    @Step
    @Description("Base url Delete token DELETE {url}/token/")
    public Response deleteToken(User user) {
        return executeRequest("DELETE", user);
    }

    private Response executeRequest(String method, User user) {
        try {
            Response response = given().spec(baseSpec())
                    .body(user)
                    .log().all()
                    .when()
                    .request(method, "/token/");

            // Логирование ответа для Allure
            response.then().log().all();
            return response;
        } catch (Exception e) {
            // Логирование исключений
            System.err.println("Error occurred while processing token: " + e.getMessage());
            throw new RuntimeException("Failed to execute request", e);
        }
    }
}
