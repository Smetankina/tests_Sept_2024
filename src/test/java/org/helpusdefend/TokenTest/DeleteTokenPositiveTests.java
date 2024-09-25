package org.helpusdefend.TokenTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import org.helpusdefend.client.TokenClient;
import org.helpusdefend.model.RandomUser;
import org.helpusdefend.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class DeleteTokenPositiveTests {


    TokenClient tokenClient;
    User user;

    @BeforeEach
    public void setUp() {
        tokenClient = new TokenClient();
        user = new RandomUser();
    }

    @Test
    @Description("Verify successful deletion of user token returns HTTP 200 OK")
    @DisplayName("Success delete token returns 200 OK")
    public void deleteUserTokenSuccessfullyTest() throws IOException {
        createUserAndToken();
        deleteUserToken(200); // Expect for the token to be successfully deleted
    }

    @Test
    @Description("Verify deletion of non-existent user token returns 404 Not Found")
    @DisplayName("Attempt to delete non-existent token returns 404 Not Found")
    public void deleteNonExistentTokenTest() throws IOException {
        createUserAndToken();
        deleteUserToken(200); // Expect for the token to be successfully deleted first time
        deleteUserToken(404); // Trying to delete an already deleted token, expect 404
    }

    @Step("Create user token")
    private void createUserAndToken() throws IOException {
        tokenClient.createToken(user);
    }

    @Step("Delete user token and validate status code")
    private void deleteUserToken(int expectStatusCode) throws IOException {
        tokenClient.deleteToken(user).then().statusCode(equalTo(expectStatusCode));
    }


    }

