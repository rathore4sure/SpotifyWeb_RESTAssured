package com.spotify.services;

import com.spotify.auth.TokenManager;
import com.spotify.pojo.Playlist;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PlaylistService {

    public Response createPlaylist(String userId, Playlist playlist) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .contentType("application/json")
                .pathParam("user_id", userId)
                .body(playlist)
                .when()
                .post("/users/{user_id}/playlists")
                .then()
                .extract()
                .response();
    }

    public Response getPlaylist(String playlistId) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .pathParam("playlist_id", playlistId)
                .when()
                .get("/playlists/{playlist_id}");
    }

    public Response updatePlaylist(String playlistId, Playlist playlist) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .contentType("application/json")
                .pathParam("playlist_id", playlistId)
                .body(playlist)
                .when()
                .put("/playlists/{playlist_id}");
    }

    public Response getCurrUsersPlaylists(int limit, int offset) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get("/me/playlists");
    }
}
