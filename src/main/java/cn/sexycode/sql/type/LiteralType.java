package cn.sexycode.sql.type;

import cn.sexycode.sql.dialect.Dialect;

/**
 * Additional contract for a {@link Type} that may appear as an SQL literal
 *
 */
public interface LiteralType<T> {
    /**
     * Convert the value into a string representation, suitable for embedding in an SQL statement as a
     * literal.
     *
     * @param value   The value to convert
     * @param dialect The SQL dialect
     * @return The value's string representation
     * @throws Exception Indicates an issue converting the value to literal string.
     */
    String objectToSQLString(T value, Dialect dialect) throws Exception;

}
