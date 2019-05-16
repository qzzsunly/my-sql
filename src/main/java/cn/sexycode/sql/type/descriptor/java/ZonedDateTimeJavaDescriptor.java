package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.ZonedDateTimeType;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Java type descriptor for the LocalDateTime type.
 *
 */
public class ZonedDateTimeJavaDescriptor extends AbstractTypeDescriptor<ZonedDateTime> {
    /**
     * Singleton access
     */
    public static final ZonedDateTimeJavaDescriptor INSTANCE = new ZonedDateTimeJavaDescriptor();

    @SuppressWarnings("unchecked")
    public ZonedDateTimeJavaDescriptor() {
        super(ZonedDateTime.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(ZonedDateTime value) {
        return ZonedDateTimeType.FORMATTER.format(value);
    }

    @Override
    public ZonedDateTime fromString(String string) {
        return ZonedDateTime.from(ZonedDateTimeType.FORMATTER.parse(string));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(ZonedDateTime zonedDateTime, Class<X> type, WrapperOptions options) {
        if (zonedDateTime == null) {
            return null;
        }

        if (ZonedDateTime.class.isAssignableFrom(type)) {
            return (X) zonedDateTime;
        }

        if (Calendar.class.isAssignableFrom(type)) {
            return (X) GregorianCalendar.from(zonedDateTime);
        }

        if (Timestamp.class.isAssignableFrom(type)) {
            return (X) Timestamp.from(zonedDateTime.toInstant());
        }

        if (java.sql.Date.class.isAssignableFrom(type)) {
            return (X) java.sql.Date.from(zonedDateTime.toInstant());
        }

        if (java.sql.Time.class.isAssignableFrom(type)) {
            return (X) java.sql.Time.from(zonedDateTime.toInstant());
        }

        if (Date.class.isAssignableFrom(type)) {
            return (X) Date.from(zonedDateTime.toInstant());
        }

        if (Long.class.isAssignableFrom(type)) {
            return (X) Long.valueOf(zonedDateTime.toInstant().toEpochMilli());
        }

        throw unknownUnwrap(type);
    }

    @Override
    public <X> ZonedDateTime wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (ZonedDateTime.class.isInstance(value)) {
            return (ZonedDateTime) value;
        }

        if (Timestamp.class.isInstance(value)) {
            final Timestamp ts = (Timestamp) value;
            return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }

        if (Date.class.isInstance(value)) {
            final Date date = (Date) value;
            return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }

        if (Long.class.isInstance(value)) {
            return ZonedDateTime.ofInstant(Instant.ofEpochMilli((Long) value), ZoneId.systemDefault());
        }

        if (Calendar.class.isInstance(value)) {
            final Calendar calendar = (Calendar) value;
            return ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        }

        throw unknownWrap(value.getClass());
    }
}
