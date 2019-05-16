package cn.sexycode.sql.type;

/**
 * A custom type that may function as an identifier or discriminator type
 *
 */
public interface EnhancedUserType extends UserType {
    /**
     * Return an SQL literal representation of the value
     */
    String objectToSQLString(Object value);
}
