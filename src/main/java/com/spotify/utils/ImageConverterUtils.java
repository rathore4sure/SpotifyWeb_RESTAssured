package com.spotify.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageConverterUtils {

    public static String encodeImageToBase64(String imageFilePath) {
        Path imagePath = Paths.get(imageFilePath);
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            System.err.println("Error reading image file: " + e.getMessage());
            return null;
        }

        if (imageBytes != null) {
            return Base64.getEncoder().encodeToString(imageBytes);

        }
        return null;

    }

    public static byte[] encodeImageToBytes(String imageFilePath) {
        Path imagePath = Paths.get(imageFilePath);
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            System.err.println("Error reading image file: " + e.getMessage());
            return null;
        }
        return imageBytes;
    }
}
