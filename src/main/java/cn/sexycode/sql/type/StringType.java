package cn.sexycode.sql.type;

import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.StringTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#VARCHAR VARCHAR} and {@link String}
 *
 */
public class StringType
        extends AbstractSingleColumnStandardBasicType<String>
        implements DiscriminatorType<String> {

    public static final StringType INSTANCE = new StringType();

    public StringType() {
        super(VarcharTypeDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "string";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

    public String objectToSQLString(String value, Dialect dialect) throws Exception {
        return '\'' + value + '\'';
    }

    public String stringToObject(String xml) throws Exception {
        return xml;
    }

    public String toString(String value) {
        return value;
    }
}
