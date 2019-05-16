package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.OffsetTimeJavaDescriptor;
import cn.sexycode.sql.type.descriptor.sql.TimeTypeDescriptor;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 */
public class OffsetTimeType
        extends AbstractSingleColumnStandardBasicType<OffsetTime>
        implements LiteralType<OffsetTime> {

    /**
     * Singleton access
     */
    public static final OffsetTimeType INSTANCE = new OffsetTimeType();

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.S xxxxx", Locale.ENGLISH);

    public OffsetTimeType() {
        super(TimeTypeDescriptor.INSTANCE, OffsetTimeJavaDescriptor.INSTANCE);
    }

    @Override
    public String objectToSQLString(OffsetTime value, Dialect dialect) throws Exception {
        return "{t '" + FORMATTER.format(value) + "'}";
    }

    @Override
    public String getName() {
        return OffsetTime.class.getSimpleName();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
