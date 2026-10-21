package com.jobplatform.automation.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                PROPERTIES.load(input);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigReader() {}

    public static String get(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) return systemValue;

        String envKey = key.toUpperCase().replace('.', '_');
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) return envValue;

        String propertyValue = PROPERTIES.getProperty(key);
        return propertyValue == null || propertyValue.isBlank() ? defaultValue : propertyValue;
    }

    public static String require(String key) {
        String value = get(key, "");
        if (value.isBlank()) {
            throw new IllegalStateException("Missing required configuration: " + key);
        }
        return value;
    }

    public static int getInt(String key, int defaultValue) {
        return Integer.parseInt(get(key, Integer.toString(defaultValue)));
    }
}
