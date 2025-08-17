package com.spotify.services;

import com.spotify.auth.TokenManager;
import com.spotify.auth.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AlbumService extends BaseServiceClass{

    public Response getAlbum(String albumId) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .pathParam("album_id", albumId)
                .when()
                .get("/albums/{album_id}");
    }

    public Response getSeveralAlbums(String albumIds) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .queryParam("ids", albumIds)
                .when()
                .get("/albums");
    }
}
