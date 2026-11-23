
package com.de_residency.auth.database;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {

    T map(ResultSet result) throws SQLException;
}
