package com.de_residency.auth.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.de_residency.auth.utils.Utils;

public class ConnectionPool {
    private final DatabaseConnection databaseConnection;
    private final BlockingQueue<Connection> connections;
    private final int poolSize;
    private final int timeout;

    public ConnectionPool(DatabaseConnection newConnection) throws SQLException {
        this.databaseConnection = newConnection;
        this.poolSize = Utils.getPositiveInt("DB_POOL_SIZE");
        this.timeout = Utils.getPositiveInt("DB_CONNECTION_TIMEOUT");
        this.connections = new ArrayBlockingQueue<Connection>(poolSize);

        try {
            for (int i = 0; i < this.poolSize; i++) {
                this.connections.add(this.databaseConnection.connect());
            }
        } catch (SQLException ex) {
            for (Connection connection : this.connections) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                    ex.addSuppressed(closeException);
                }
            }

            throw new SQLException("Error while pool initializing", ex);
        }
    }

    public Connection getConnection() throws SQLException, InterruptedException {
        Connection conn = this.connections.poll(this.timeout, TimeUnit.SECONDS);

        if (conn == null) {
            throw new SQLException("No database connection currently available");
        }

        return conn;
    }

    public boolean releaseConnection(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connection is null");
        }

        try {
            if (!conn.isClosed() && conn.isValid(this.timeout)) {
                return this.connections.offer(conn);
            }

            return false;
        } catch (SQLException ex) {
            throw new SQLException("Error while releasing the connection", ex);
        }
    }
}