package org.helpusdefend.TokenTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.helpusdefend.client.TokenClient;
import org.helpusdefend.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

public class CreateTokenNegativeTests {
    TokenClient tokenClient;
    User user;

    @BeforeEach
    public void setUp() {
        tokenClient = new TokenClient();
    }

    // Method providing test data (various invalid user objects)
    static Stream<User> provideInvalidUsers() {
        return Stream.of(
                new User(RandomStringUtils.random(50, false, true)), // Missing token field
                new User(), // Missing both id and token fields
                new User("", RandomStringUtils.random(50, true, true)), // Empty id field
                new User(RandomStringUtils.random(50, false, true), "") // Empty token field
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUsers")
    @Description("Check that creating a token with invalid data returns 400 Bad Request")
    @DisplayName("Test creating token with invalid data")
    public void createUserTokenNegativeTest(User invalidUser) throws IOException {
        logTestData(invalidUser);
        sendCreateTokenRequest(invalidUser);
    }

    @Step("Send request to create token with invalid data")
    public void sendCreateTokenRequest(User user) throws IOException {
        String payload = user.toString(); // Convert the User object to a string (assuming the toString() method is correctly defined)
        Allure.addAttachment("Request Payload", "application/json", payload);
        tokenClient.createToken(user)
                .then()
                .statusCode(400)
                .log().body(); // Log the response in the Allure report
    }

    @Step("Test data: User object")
    public void logTestData(User user) {
        Allure.addAttachment("User Data", user.toString());
    }

    @AfterEach
    public void tearDown() {
        if (user != null && user.getToken() != null) {
            tokenClient.deleteToken(user);
        }
    }
}
