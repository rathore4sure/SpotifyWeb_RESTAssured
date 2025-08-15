package com.spotify.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    // Read property
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("Error reading property from " + fileName, e);
        }
    }

    // Read property with default value
    public static String getProperty(String fileName, String key, String defaultValue) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return properties.getProperty(key, defaultValue);
        } catch (IOException e) {
            return defaultValue;
        }
    }

    // Write/Update property
    public static void setProperty(String fileName, String key, String value) {
        Properties properties = new Properties();

        // Load existing properties if file exists
        File file = new File(fileName);
        if (file.exists()) {
            try (InputStream input = new FileInputStream(file)) {
                properties.load(input);
            } catch (IOException ignored) {
            }
        }

        properties.setProperty(key, value);

        try (OutputStream output = new FileOutputStream(fileName)) {
            properties.store(output, "Updated by PropertyUtils");
        } catch (IOException e) {
            throw new RuntimeException("Error saving property to " + fileName, e);
        }
    }
}
