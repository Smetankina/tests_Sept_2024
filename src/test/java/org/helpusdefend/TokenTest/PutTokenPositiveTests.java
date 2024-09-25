package org.helpusdefend.TokenTest;

import io.qameta.allure.Description;
import org.helpusdefend.client.TokenClient;
import org.helpusdefend.model.RandomUser;
import org.helpusdefend.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PutTokenPositiveTests {
    TokenClient tokenClient;
    User user;

    @BeforeEach
    public void setUp(){
        tokenClient = new TokenClient();
        user = new RandomUser();
    }

    @Test
    @Description("Test Case 2.1: Token Found")
    @DisplayName("Test Case 2.1: Token Found returns 200 OK")
    public void putUserTokenSuccessfullyTest(){
        tokenClient.createToken(user);
        tokenClient.checkToken(user)
                .then()
                .statusCode(200);    };

    @AfterEach
    public void tearDown(){
        tokenClient.deleteToken(user);
    }
}
