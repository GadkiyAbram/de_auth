
package com.de_residency.auth.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private final ConnectionPool pool;

    public Database(ConnectionPool pool) {
        this.pool = pool;
    }

    public <T> T exec(
            String sql,
            RowMapper<T> mapper,
            Object... parameters
    ) throws SQLException, InterruptedException {

        Connection connection = pool.getConnection();

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                for (int i = 0; i < parameters.length; i++) {
                    statement.setObject(i + 1, parameters[i]);
                }

                try (ResultSet result = statement.executeQuery()) {
                    return mapper.map(result);
                }
            }
        } finally {
            pool.releaseConnection(connection);
        }
    }
}
