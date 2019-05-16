package cn.sexycode.sql.type;

/**
 * Marker interface for basic types.
 *
 * @author qzz
 */
public interface BasicType extends Type {
    /**
     * Get the names under which this type should be registered in the type registry.
     *
     * @return The keys under which to register this type.
     */
    String[] getRegistrationKeys();
}
