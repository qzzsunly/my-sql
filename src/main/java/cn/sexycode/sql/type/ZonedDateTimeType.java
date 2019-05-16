package cn.sexycode.sql.type;

import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.ZonedDateTimeJavaDescriptor;
import cn.sexycode.sql.type.descriptor.sql.TimestampTypeDescriptor;
import cn.sexycode.sql.util.ZonedDateTimeComparator;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

/**
 */
public class ZonedDateTimeType
        extends AbstractSingleColumnStandardBasicType<ZonedDateTime>
        implements VersionType<ZonedDateTime>, LiteralType<ZonedDateTime> {

    /**
     * Singleton access
     */
    public static final ZonedDateTimeType INSTANCE = new ZonedDateTimeType();

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S VV", Locale.ENGLISH);

    public ZonedDateTimeType() {
        super(TimestampTypeDescriptor.INSTANCE, ZonedDateTimeJavaDescriptor.INSTANCE);
    }

    @Override
    public String objectToSQLString(ZonedDateTime value, Dialect dialect) throws Exception {
        return "{ts '" + FORMATTER.format(value) + "'}";
    }

    @Override
    @SuppressWarnings("unchecked")
    public Comparator<ZonedDateTime> getComparator() {
        return ZonedDateTimeComparator.INSTANCE;
    }

    @Override
    public String getName() {
        return ZonedDateTime.class.getSimpleName();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
