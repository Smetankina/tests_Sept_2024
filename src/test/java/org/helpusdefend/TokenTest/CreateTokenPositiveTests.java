package org.helpusdefend.TokenTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.helpusdefend.client.TokenClient;
import org.helpusdefend.model.RandomUser;
import org.helpusdefend.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class CreateTokenPositiveTests {

    TokenClient tokenClient;
    User user;

    @BeforeEach
    public void setUp() {
        tokenClient = new TokenClient();
        user = new RandomUser();
    }

    @Test
    @Description("Test Case 1.1: Successful Token Creation returns HTTP 200 OK")
    @DisplayName("Success creation token returns 200 OK")
    public void createUserTokenSuccessfullyTest() throws IOException {
        createUserAndToken(200);
    }

    @Step("Create user token")
    private void createUserAndToken(int expectStatusCode) {
        tokenClient.createToken(user).then().statusCode(equalTo(expectStatusCode));
    }

    @AfterEach
    public void tearDown() {
        tokenClient.deleteToken(user);
    }
}
