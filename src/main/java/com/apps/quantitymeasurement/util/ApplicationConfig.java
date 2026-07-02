package com.apps.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties =
            new Properties();

    static {

        try (InputStream input =
                     ApplicationConfig.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "application.properties")) {

            if (input == null) {

                throw new RuntimeException(
                        "application.properties not found."
                );
            }

            properties.load(input);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load configuration.",
                    e
            );
        }
    }

    private ApplicationConfig() {
    }

    public static String getProperty(
            String key
    ) {

        return properties.getProperty(key);
    }
}