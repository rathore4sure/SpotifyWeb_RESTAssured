package com.spotify.auth;

import com.spotify.utils.PropertyUtils;
import io.restassured.response.Response;
import java.time.Instant;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static final String TOKEN_FILE = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\token.properties";
//    private static final String CLIENT_ID = PropertyUtils.getProperty(TOKEN_FILE, "clientID");
//    private static final String CLIENT_SECRET = PropertyUtils.getProperty(TOKEN_FILE, "clientSecret");
    private static final String TOKEN_URL = PropertyUtils.getProperty(TOKEN_FILE, "tokenURL", "https://accounts.spotify.com/api/token");
    private static final String refreshToken = PropertyUtils.getProperty(TOKEN_FILE, "refreshToken");
    private static final String authCode = PropertyUtils.getProperty(TOKEN_FILE, "authCode");
    private static final Instant storedExpiryTime = Instant.parse(PropertyUtils.getProperty(TOKEN_FILE, "storedExpiryTime"));
    private static final String storedAccessToken = PropertyUtils.getProperty(TOKEN_FILE, "storedAccessToken");

    public synchronized static String getToken() {

        if (storedAccessToken == null || Instant.now().isAfter(storedExpiryTime)) {
            System.out.println("Refreshing Spotify access token...");
            // if the stored access token is null or expired, refresh it
            refreshToken();
        }
            // if the stored access token is not null or expired, simply return it
                return storedAccessToken;
        }


    private static void refreshToken() {
        Response response =
                given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + authCode)
                .formParam("refresh_token", refreshToken)
                .formParam("grant_type", "refresh_token")

//                    .formParam("client_id", System.getProperty("clientId"));
//                    .formParam("client_secret", System.getProperty("clientSecret"));

                .post("https://accounts.spotify.com/api/token");

        if(response.statusCode() != 200) {
            throw new RuntimeException("Failed to refresh token: " + response.getStatusLine());
        }else {
            int expiresIn = response.jsonPath().getInt("expires_in");
            String accessToken = response.jsonPath().getString("access_token");

            Instant expiryTime = Instant.now().plusSeconds(expiresIn - 300); // 1 min buffer

            // store the new access token and expiry time
            PropertyUtils.setProperty(TOKEN_FILE, "storedAccessToken", accessToken);
            PropertyUtils.setProperty(TOKEN_FILE, "storedExpiryTime", String.valueOf(expiryTime));

        }

    }
}
