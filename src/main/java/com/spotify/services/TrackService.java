package com.spotify.services;

import com.spotify.auth.TokenManager;
import com.spotify.auth.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrackService extends BaseServiceClass{

    public Response addTracksToPlaylist(String playlistId, String uris) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .contentType("application/json")
                .pathParam("playlist_id", playlistId)
                .queryParam("uris", uris)
                .when()
                .post("/playlists/{playlist_id}/tracks");
    }

    public Response removeTracksFromPlaylist(String playlistId, Object removeRequestBody) {
        return given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .contentType("application/json")
                .pathParam("playlist_id", playlistId)
                .body(removeRequestBody)
                .when()
                .delete("/playlists/{playlist_id}/tracks");
    }
}
