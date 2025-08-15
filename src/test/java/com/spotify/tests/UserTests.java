package com.spotify.tests;

import com.spotify.services.UserService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    private UserService userService;

    @BeforeClass
    public void setup() {
        userService = new UserService();
    }

    @Test
    public void getCurrentUserProfileTest() {
        Response response = userService.getCurrentUserProfile();
        response.then().statusCode(200);
    }

    @Test
    public void getUserProfileTest() {
        String userId = "spotify_user_id"; // Replace with a valid user ID
        Response response = userService.getUserProfile(userId);
        response.then().statusCode(200);
    }
}
