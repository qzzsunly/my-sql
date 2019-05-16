package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.BooleanTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.CharTypeDescriptor;

import java.io.Serializable;

/**
 * A type that maps between {@link java.sql.Types#CHAR CHAR(1)} and {@link Boolean} (using 'T' and 'F')
 *
 */
public class TrueFalseType
        extends AbstractSingleColumnStandardBasicType<Boolean>
        implements PrimitiveType<Boolean>, DiscriminatorType<Boolean> {

    public static final TrueFalseType INSTANCE = new TrueFalseType();

    public TrueFalseType() {
        super(CharTypeDescriptor.INSTANCE, new BooleanTypeDescriptor('T', 'F'));
    }

    @Override
    public String getName() {
        return "true_false";
    }

    @Override
    public Class getPrimitiveClass() {
        return boolean.class;
    }

    @Override
    public Boolean stringToObject(String xml) throws Exception {
        return fromString(xml);
    }

    @Override
    public Serializable getDefaultValue() {
        return Boolean.FALSE;
    }

    @Override
    public String objectToSQLString(Boolean value, Dialect dialect) throws Exception {
        return StringType.INSTANCE.objectToSQLString(value ? "T" : "F", dialect);
    }

}
