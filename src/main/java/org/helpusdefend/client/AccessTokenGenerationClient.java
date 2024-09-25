package org.defend.client;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;



import static io.restassured.RestAssured.baseURI;

public class AccessTokenGenerationClient {

    public static String readProperties(String property) throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        return System.getProperty(property);
    }

    protected RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .build();
    }
}


