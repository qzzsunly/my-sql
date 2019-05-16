package cn.sexycode.sql.type.descriptor;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Contract for binding values to a {@link PreparedStatement}.
 *
 * @author qzz
 */
public interface ValueBinder<X> {
    /**
     * Bind a value to a prepared statement.
     *
     * @param st      The prepared statement to which to bind the value.
     * @param value   The value to bind.
     * @param index   The position at which to bind the value within the prepared statement
     * @param options The options.
     * @throws SQLException Indicates a JDBC error occurred.
     */
    public void bind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException;

    /**
     * Bind a value to a CallableStatement.
     *
     * @param st      The prepared statement to which to bind the value.
     * @param value   The value to bind.
     * @param name    The name to bind the value within the prepared statement
     * @param options The options.
     * @throws SQLException Indicates a JDBC error occurred.
     */
    public void bind(CallableStatement st, X value, String name, WrapperOptions options) throws SQLException;
}
