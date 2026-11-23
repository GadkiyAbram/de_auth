package com.de_residency.auth.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final DatabaseConfig config;

    public DatabaseConnection(DatabaseConfig config) {
        this.config = config;
    }

    public Connection connect() throws SQLException {

        String url = switch (this.config.getType()) {

            case DatabaseType.POSTGRES ->
                    "jdbc:postgresql://%s:%d/%s".formatted(
                            config.getHost(),
                            config.getPort(),
                            config.getDatabase()
                    );

            case DatabaseType.MYSQL ->
                    "jdbc:mysql://%s:%d/%s".formatted(
                            config.getHost(),
                            config.getPort(),
                            config.getDatabase()
                    );
        };

        return DriverManager.getConnection(
                url,
                config.getUsername(),
                config.getPassword()
        );
    }
}
