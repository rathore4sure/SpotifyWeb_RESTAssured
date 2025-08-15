package com.spotify.tests;

import com.spotify.services.AlbumService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlbumTests {

    private AlbumService albumService;

    @BeforeClass
    public void setup() {
        albumService = new AlbumService();
    }

    @Test
    public void getAlbumTest() {
        String albumId = "4aawyAB9vmqN3uQ7FjRGTy"; // Example album ID
        Response response = albumService.getAlbum(albumId);
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralAlbumsTest() {
        String albumIds = "4aawyAB9vmqN3uQ7FjRGTy,0sNOF9WDwhWunNAHPD3Baj";
        Response response = albumService.getSeveralAlbums(albumIds);
        response.then().statusCode(200);
    }
}
