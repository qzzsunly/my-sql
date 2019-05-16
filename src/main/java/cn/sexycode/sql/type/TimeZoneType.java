package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.TimeZoneTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.TimeZone;

/**
 * A type mapping {@link java.sql.Types#VARCHAR VARCHAR} and {@link TimeZone}
 *
 */
public class TimeZoneType
        extends AbstractSingleColumnStandardBasicType<TimeZone>
        implements LiteralType<TimeZone> {

    public static final TimeZoneType INSTANCE = new TimeZoneType();

    public TimeZoneType() {
        super(VarcharTypeDescriptor.INSTANCE, TimeZoneTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "timezone";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

    public String objectToSQLString(TimeZone value, Dialect dialect) throws Exception {
        return StringType.INSTANCE.objectToSQLString(value.getID(), dialect);
    }

}
