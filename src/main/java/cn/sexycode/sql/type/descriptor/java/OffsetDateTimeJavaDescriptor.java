package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.OffsetDateTimeType;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Java type descriptor for the LocalDateTime type.
 *
 */
public class OffsetDateTimeJavaDescriptor extends AbstractTypeDescriptor<OffsetDateTime> {
    /**
     * Singleton access
     */
    public static final OffsetDateTimeJavaDescriptor INSTANCE = new OffsetDateTimeJavaDescriptor();

    @SuppressWarnings("unchecked")
    public OffsetDateTimeJavaDescriptor() {
        super(OffsetDateTime.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(OffsetDateTime value) {
        return OffsetDateTimeType.FORMATTER.format(value);
    }

    @Override
    public OffsetDateTime fromString(String string) {
        return OffsetDateTime.from(OffsetDateTimeType.FORMATTER.parse(string));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(OffsetDateTime offsetDateTime, Class<X> type, WrapperOptions options) {
        if (offsetDateTime == null) {
            return null;
        }

        if (OffsetDateTime.class.isAssignableFrom(type)) {
            return (X) offsetDateTime;
        }

        if (Calendar.class.isAssignableFrom(type)) {
            return (X) GregorianCalendar.from(offsetDateTime.toZonedDateTime());
        }

        if (Timestamp.class.isAssignableFrom(type)) {
            return (X) Timestamp.from(offsetDateTime.toInstant());
        }

        if (java.sql.Date.class.isAssignableFrom(type)) {
            return (X) java.sql.Date.from(offsetDateTime.toInstant());
        }

        if (java.sql.Time.class.isAssignableFrom(type)) {
            return (X) java.sql.Time.from(offsetDateTime.toInstant());
        }

        if (Date.class.isAssignableFrom(type)) {
            return (X) Date.from(offsetDateTime.toInstant());
        }

        if (Long.class.isAssignableFrom(type)) {
            return (X) Long.valueOf(offsetDateTime.toInstant().toEpochMilli());
        }

        throw unknownUnwrap(type);
    }

    @Override
    public <X> OffsetDateTime wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (OffsetDateTime.class.isInstance(value)) {
            return (OffsetDateTime) value;
        }

        if (Timestamp.class.isInstance(value)) {
            final Timestamp ts = (Timestamp) value;
            return OffsetDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }

        if (Date.class.isInstance(value)) {
            final Date date = (Date) value;
            return OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }

        if (Long.class.isInstance(value)) {
            return OffsetDateTime.ofInstant(Instant.ofEpochMilli((Long) value), ZoneId.systemDefault());
        }

        if (Calendar.class.isInstance(value)) {
            final Calendar calendar = (Calendar) value;
            return OffsetDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        }

        throw unknownWrap(value.getClass());
    }
}
