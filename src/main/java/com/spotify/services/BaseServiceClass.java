package com.spotify.services;

import com.spotify.auth.TokenManager;
import com.spotify.utils.PropertyUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseServiceClass {

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\APIService.properties";
    private static final String BASE_URI = PropertyUtils.getProperty(FILE_PATH, "baseURI");
    private static final String BASE_PATH = PropertyUtils.getProperty(FILE_PATH, "basePath");
    private static final String CONTENT_TYPE = PropertyUtils.getProperty(FILE_PATH, "contentType");
    private static final Boolean LOG_ALL = Boolean.valueOf(PropertyUtils.getProperty(FILE_PATH, "logAll"));
    /**
     * Builds a RequestSpecification with Authorization header
     */
    protected static RequestSpecification getRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addHeader("Authorization", "Bearer " + TokenManager.getToken())
                .addHeader("Content-Type", CONTENT_TYPE);

        if (LOG_ALL) {
            builder.log(io.restassured.filter.log.LogDetail.ALL);
        }
        return builder.build();
    }

    /**
     * Reusable GET method for API calls
     */
    protected Response GET(String endpoint){
        return given()
                .spec(getRequestSpec())
                .when()
                .get(endpoint);
    }
    /**
     * Reusable POST method for API calls
     */
    protected Response POST(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);
    }

    /**
     * Reusable PUT method for API calls
     */
    protected Response PUT(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .put(endpoint);
    }

    /**
     * Reusable DELETE method for API calls
     */
    protected Response DELETE(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .delete(endpoint);
    }

    /**
     * Reusable PATCH method for API calls
     */
    protected Response PATCH(String endpoint, Object body) {
        return given()
                .spec(getRequestSpec())
                .body(body)
                .when()
                .patch(endpoint);
    }

}
