package com.spotify.tests;

import com.spotify.services.TrackService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TrackTests {

    private TrackService trackService;
    private String playlistId = "YOUR_TEST_PLAYLIST_ID"; // Must exist
    private String trackUri = "spotify:track:4uLU6hMCjMI75M1A2tKUQC"; // Example track URI

    @BeforeClass
    public void setup() {
        trackService = new TrackService();
    }

    @Test
    public void addTracksToPlaylistTest() {
        Response response = trackService.addTracksToPlaylist(playlistId, trackUri);
        response.then().statusCode(201);
    }

    @Test(dependsOnMethods = "addTracksToPlaylistTest")
    public void removeTracksFromPlaylistTest() {
        String body = "{ \"tracks\": [{ \"uri\": \"" + trackUri + "\" }] }";
        Response response = trackService.removeTracksFromPlaylist(playlistId, body);
        response.then().statusCode(200);
    }
}
