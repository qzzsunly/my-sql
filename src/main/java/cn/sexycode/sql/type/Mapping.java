package cn.sexycode.sql.type;

/**
 * Defines operations common to "compiled" mappings (ie. <tt>SessionFactory</tt>)
 * and "uncompiled" mappings (ie. <tt>Configuration</tt>) that are used by
 * implementors of <tt>Type</tt>.
 *
 * @author qzz
 */
public interface Mapping {

    Type getIdentifierType(String className) throws TypeException;

    String getIdentifierPropertyName(String className) throws TypeException;

    Type getReferencedPropertyType(String className, String propertyName) throws TypeException;
}
