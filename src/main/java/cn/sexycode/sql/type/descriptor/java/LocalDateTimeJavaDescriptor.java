package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.LocalDateTimeType;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Java type descriptor for the LocalDateTime type.
 *
 */
public class LocalDateTimeJavaDescriptor extends AbstractTypeDescriptor<LocalDateTime> {
    /**
     * Singleton access
     */
    public static final LocalDateTimeJavaDescriptor INSTANCE = new LocalDateTimeJavaDescriptor();

    @SuppressWarnings("unchecked")
    public LocalDateTimeJavaDescriptor() {
        super(LocalDateTime.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(LocalDateTime value) {
        return LocalDateTimeType.FORMATTER.format(value);
    }

    @Override
    public LocalDateTime fromString(String string) {
        return LocalDateTime.from(LocalDateTimeType.FORMATTER.parse(string));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(LocalDateTime value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (LocalDateTime.class.isAssignableFrom(type)) {
            return (X) value;
        }

        if (Timestamp.class.isAssignableFrom(type)) {
            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            return (X) Timestamp.from(instant);
        }

        if (java.sql.Date.class.isAssignableFrom(type)) {
            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            return (X) java.sql.Date.from(instant);
        }

        if (java.sql.Time.class.isAssignableFrom(type)) {
            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            return (X) java.sql.Time.from(instant);
        }

        if (Date.class.isAssignableFrom(type)) {
            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            return (X) Date.from(instant);
        }

        if (Calendar.class.isAssignableFrom(type)) {
            return (X) GregorianCalendar.from(value.atZone(ZoneId.systemDefault()));
        }

        if (Long.class.isAssignableFrom(type)) {
            Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
            return (X) Long.valueOf(instant.toEpochMilli());
        }

        throw unknownUnwrap(type);
    }

    @Override
    public <X> LocalDateTime wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (LocalDateTime.class.isInstance(value)) {
            return (LocalDateTime) value;
        }

        if (Timestamp.class.isInstance(value)) {
            final Timestamp ts = (Timestamp) value;
            return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }

        if (Long.class.isInstance(value)) {
            final Instant instant = Instant.ofEpochMilli((Long) value);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

        if (Calendar.class.isInstance(value)) {
            final Calendar calendar = (Calendar) value;
            return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        }

        if (Date.class.isInstance(value)) {
            final Timestamp ts = (Timestamp) value;
            final Instant instant = Instant.ofEpochMilli(ts.getTime());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

        throw unknownWrap(value.getClass());
    }
}
