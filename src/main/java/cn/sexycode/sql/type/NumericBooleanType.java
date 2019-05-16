package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.BooleanTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.IntegerTypeDescriptor;

import java.io.Serializable;

/**
 * A type that maps between {@link java.sql.Types#INTEGER INTEGER} and {@link Boolean} (using 1 and 0)
 *
 */
public class NumericBooleanType
        extends AbstractSingleColumnStandardBasicType<Boolean>
        implements PrimitiveType<Boolean>, DiscriminatorType<Boolean> {

    public static final NumericBooleanType INSTANCE = new NumericBooleanType();

    public NumericBooleanType() {
        super(IntegerTypeDescriptor.INSTANCE, BooleanTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "numeric_boolean";
    }

    @Override
    public Class getPrimitiveClass() {
        return boolean.class;
    }

    @Override
    public Serializable getDefaultValue() {
        return Boolean.FALSE;
    }

    @Override
    public Boolean stringToObject(String string) {
        return fromString(string);
    }

    @Override
    public String objectToSQLString(Boolean value, Dialect dialect) {
        return value ? "1" : "0";
    }
}
