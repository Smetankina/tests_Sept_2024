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

public class CreateTokenNegativeTests {
    TokenClient tokenClient;
    User user;

    @BeforeEach
    public void setUp() {
        tokenClient = new TokenClient();
        user = new RandomUser();
    }

    //Missing field token
    @Test
    @Description("Test Case 1.2.1: Missing Fields Token in Request  returns 400 Bad request")
    @DisplayName("Missing Token Fields in Request returns 400 Bad request")
    public void createUserTokenMissingTokenFieldTest() throws IOException {
        tokenClient.createToken(new User(RandomStringUtils.random(50, false, true)))
                .then().statusCode(400);
    }

    //Missing field id and token Body:{}
    @Test
    @Description("Test Case 1.2.2: Missing Fields in Request returns 400 Bad request")
    @DisplayName("Missing Id Token Fields in Request returns 400 Bad request")
    public void createUserTokenMissingFieldTest() throws IOException {
        tokenClient.createToken(new User())
                .then().statusCode(400);
    }

    //Empty id:""
    @Test
    @Description("Test Case 1.3.1: Invalid Data Types (the empty Id field) returns 400 Bad request")
    @DisplayName("Empty Id Fields in Request returns 400 Bad request")
    public void createUserTokenEmptyIdFieldTest() throws IOException {
        tokenClient.createToken(new User("", RandomStringUtils.random(50, true, true)))
                .then().statusCode(400);
    }

    //Empty token:""
    @Test
    @Description("Test Case 1.3.2: Invalid Data Types (the empty Token field) returns 400 Bad request")
    @DisplayName("Empty Token Fields in Request returns 400 Bad request")
    public void createUserTokenEmptyTokenFieldTest() throws IOException {
        tokenClient.createToken(new User(RandomStringUtils.random(50, false, true)))
                .then().statusCode(400);
    }


    @AfterEach
    public void tearDown() {
        tokenClient.deleteToken(user);
    }
}
