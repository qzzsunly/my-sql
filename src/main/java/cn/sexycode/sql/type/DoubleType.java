package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.DoubleTypeDescriptor;

import java.io.Serializable;

/**
 * A type that maps between {@link java.sql.Types#DOUBLE DOUBLE} and {@link Double}
 *
 */
public class DoubleType extends AbstractSingleColumnStandardBasicType<Double> implements PrimitiveType<Double> {
    public static final DoubleType INSTANCE = new DoubleType();

    public static final Double ZERO = 0.0;

    public DoubleType() {
        super(cn.sexycode.sql.type.descriptor.sql.DoubleTypeDescriptor.INSTANCE, DoubleTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "double";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), double.class.getName(), Double.class.getName()};
    }

    @Override
    public Serializable getDefaultValue() {
        return ZERO;
    }

    @Override
    public Class getPrimitiveClass() {
        return double.class;
    }

    @Override
    public String objectToSQLString(Double value, Dialect dialect) throws Exception {
        return toString(value);
    }
}
