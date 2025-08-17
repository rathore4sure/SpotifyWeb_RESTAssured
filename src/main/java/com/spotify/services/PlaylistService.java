package com.spotify.services;
import com.spotify.auth.TokenManager;
import com.spotify.pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class PlaylistService extends BaseServiceClass {

    public Response createPlaylist(String userId, Playlist playlist) {
        return POST("/users/"+ userId+"/playlists", playlist);
    }

    public Response getPlaylist(String playlistId) {
        return GET("/playlists/" + playlistId);
    }

    public Response updatePlaylist(String playlistId, Playlist playlist) {
        return PUT("/playlists/" + playlistId, playlist);
    }

    public Response getCurrUsersPlaylists(int limit, int offset) {
        return GET("/me/playlists" + "?limit=" + limit + "&offset=" + offset);
    }

    // A token generated with SCOPE ugc-image-upload IS REQUIRED FOR THIS METHOD
    public Response addCustomPlaylistCoverImg(String playlistId, String base64Image) {
        return given()
      /*
        Spotify requires images to be base64 encoded which we are doing with ImageConverterUtils.
      RestAssured is defaulting to URL-encoding or JSON encoding instead of sending raw bytes.
      Below here, we’re bypassing its encoder by tricking it into treating image/jpeg as “text”
        */
                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)))
                .spec(getRequestSpec())
                .header("Content-Type", "image/jpeg")
                .body(base64Image)
                .when()
                .put("/playlists/" + playlistId + "/images");
    }
}
