package com.de_residency.auth.repositories;

import java.sql.SQLException;
import java.util.Optional;
import java.sql.ResultSet;

import com.de_residency.auth.database.Database;
import com.de_residency.auth.model.User;
import com.de_residency.auth.database.DatabaseType;


public class UserRepository {

    private final Database db;

    public UserRepository(Database database) {
        this.db = database;
    }

    public Optional<User> findById(long id) throws SQLException, InterruptedException {

        String sql = """
                SELECT id, email, first_name, last_name, active
                FROM users
                WHERE id = ?
                """;

        return db.exec(
                sql,
                this::parseResult,
                id
        );
    }

    public Optional<User> findByEmail(String email) throws SQLException, InterruptedException {

        String sql = """
                SELECT id, email, first_name, last_name, active
                FROM users
                WHERE email = ?
                """;

        return db.exec(
                sql,
                this::parseResult,
                email
        );
    }

    private User mapUser(ResultSet result) throws SQLException {
        return new User(
                result.getLong("id"),
                result.getString("email"),
                result.getBoolean("active")
        );
    }

    private Optional<User> parseResult(ResultSet result) throws SQLException {
       if (!result.next()) {
            return Optional.empty();
        }

        return Optional.of(mapUser(result));
    }
}
