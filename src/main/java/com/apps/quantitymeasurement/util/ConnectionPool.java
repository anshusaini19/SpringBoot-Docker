package com.apps.quantitymeasurement.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                ApplicationConfig.getProperty("db.url")
        );

        config.setUsername(
                ApplicationConfig.getProperty("db.username")
        );

        config.setPassword(
                ApplicationConfig.getProperty("db.password")
        );

        config.setDriverClassName(
                ApplicationConfig.getProperty("db.driver")
        );

        dataSource = new HikariDataSource(config);
    }

    private ConnectionPool() {
    }

    public static Connection getConnection()
            throws SQLException {

        return dataSource.getConnection();
    }
}