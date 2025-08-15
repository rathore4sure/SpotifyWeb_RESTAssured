package com.spotify.tests;

import com.spotify.pojo.Playlist;
import com.spotify.services.PlaylistService;
import com.spotify.utils.DateTimeUtils;
import com.spotify.utils.PropertyUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaylistTests {
    private static final String USER_DETAILS_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\userdetails.properties";
    private PlaylistService playlistService;
    private final String userId = PropertyUtils.getProperty(USER_DETAILS_FILE, "userId");  // Replace with valid user ID
    private String createdPlaylistId;

    @BeforeClass
    public void setup() {
        playlistService = new PlaylistService();
    }

    @Test(priority = 1)
    public void createPlaylistTest() {
        Playlist playlist = new Playlist();
        playlist.setName("Automation Test Playlist" + " @ " + DateTimeUtils.getCurrentDateTimeInIST());
        playlist.setDescription("Playlist created by Rest Assured automation");
        playlist.setPublic(false);

        Response response = playlistService.createPlaylist(userId, playlist);
        response.then().statusCode(201);

        createdPlaylistId = response.jsonPath().getString("id");
        Assert.assertNotNull(createdPlaylistId, "Playlist ID should not be null");
    }

    @Test(priority = 2, dependsOnMethods = "createPlaylistTest")
    public void getPlaylistTest() {
        Response response = playlistService.getPlaylist(createdPlaylistId);
        response.then().statusCode(200);
        Assert.assertTrue(response.jsonPath().getString("name").contains("Automation Test Playlist"));
    }

    @Test(priority = 3, dependsOnMethods = "createPlaylistTest")
    public void updatePlaylistTest() {
        Playlist updatedPlaylist = new Playlist();
        updatedPlaylist.setName("Updated Automation Playlist");
        updatedPlaylist.setDescription("Updated description");
        updatedPlaylist.setPublic(true);

        Response response = playlistService.updatePlaylist(createdPlaylistId, updatedPlaylist);
        response.then().statusCode(200);
    }

    @Test
    public void getCurrentUsersPlaylistsTest() {
        Response response = playlistService.getCurrUsersPlaylists(5, 0);
        response.then().log().all().statusCode(200);
        Assert.assertTrue(response.jsonPath().getList("items").size() > 0, "Should return at least one playlist");
    }
}
