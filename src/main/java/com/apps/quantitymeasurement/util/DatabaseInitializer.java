package com.apps.quantitymeasurement.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    public static void initialize() {

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement();

                InputStream input =
                        DatabaseInitializer.class
                                .getClassLoader()
                                .getResourceAsStream("db/schema.sql")
        ) {

            if (input == null) {

                throw new RuntimeException(
                        "schema.sql not found."
                );
            }

            String sql =
                    new Scanner(
                            input,
                            StandardCharsets.UTF_8
                    )
                            .useDelimiter("\\A")
                            .next();

            statement.execute(sql);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Database initialization failed.",
                    e
            );
        }
    }
}