//package com.spotify.auth;
//
//import com.spotify.utils.PropertyUtils;
//import io.restassured.response.Response;
//
//import java.util.Base64;
//
//import static io.restassured.RestAssured.given;
//
//public class TokenManagerClientCredentialsType {
//
//    private static final String TOKEN_FILE = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\token.properties";
//    private static final String CLIENT_ID = PropertyUtils.getProperty(TOKEN_FILE, "clientID");
//    private static final String CLIENT_SECRET = PropertyUtils.getProperty(TOKEN_FILE, "clientSecret");
//    private static final String TOKEN_URL = PropertyUtils.getProperty(TOKEN_FILE, "tokenURL", "https://accounts.spotify.com/api/token");
//
//
//    private static String accessToken;
//    private static long expiryTime;
//
//    public static String getToken() {
//        if (accessToken == null || isTokenExpired()) {
//            System.out.println("Fetching new Spotify access token...");
//            saveTokenToProperties(generateAccessToken(), expiryTime);
//        } else {
//            System.out.println("Using cached Spotify access token.");
//        }
//        return loadTokenFromProperties();
//    }
//
//    private static boolean isTokenExpired() {
//        return System.currentTimeMillis() > expiryTime;
//    }
//
//    private static String generateAccessToken() {
//        String authString = CLIENT_ID + ":" + CLIENT_SECRET;
//        String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes());
//
//        Response response = given()
//                .header("Authorization", "Basic " + encodedAuth)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .formParam("grant_type", "client_credentials")
//                .post(TOKEN_URL)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        accessToken = response.jsonPath().getString("access_token");
//        int expiresIn = response.jsonPath().getInt("expires_in");
//        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L) - 5000;
//
//        return accessToken;
//    }
//
//    private static void saveTokenToProperties(String access_token, long expiryTime) {
//
//        PropertyUtils.setProperty(TOKEN_FILE, "accessToken", access_token);
//        PropertyUtils.setProperty(TOKEN_FILE,"expiry", String.valueOf(expiryTime));
//    }
//
//    private static String loadTokenFromProperties() {
//       return PropertyUtils.getProperty(TOKEN_FILE, "accessToken", "DefaultAccessToken");
//
//    }
//}
