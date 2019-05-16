package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.DurationJavaDescriptor;
import cn.sexycode.sql.type.descriptor.sql.BigIntTypeDescriptor;

import java.time.Duration;

/**
 */
public class DurationType
        extends AbstractSingleColumnStandardBasicType<Duration>
        implements LiteralType<Duration> {
    /**
     * Singleton access
     */
    public static final DurationType INSTANCE = new DurationType();

    public DurationType() {
        super(BigIntTypeDescriptor.INSTANCE, DurationJavaDescriptor.INSTANCE);
    }

    @Override
    public String objectToSQLString(Duration value, Dialect dialect) throws Exception {
        return String.valueOf(value.toNanos());
    }

    @Override
    public String getName() {
        return Duration.class.getSimpleName();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
