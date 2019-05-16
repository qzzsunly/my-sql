package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.StringTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.NVarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#VARCHAR VARCHAR} and {@link String}
 *
 */
public class StringNVarcharType
        extends AbstractSingleColumnStandardBasicType<String>
        implements DiscriminatorType<String> {

    public static final StringNVarcharType INSTANCE = new StringNVarcharType();

    public StringNVarcharType() {
        super(NVarcharTypeDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "nstring";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return false;
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
