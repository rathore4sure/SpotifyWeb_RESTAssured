package com.spotify.services;

import com.spotify.auth.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {

    public Response getCurrentUserProfile() {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .when()
                .get("/me");
    }

    public Response getUserProfile(String userId) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .pathParam("user_id", userId)
                .when()
                .get("/users/{user_id}");
    }
}
