package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.TypeException;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Descriptor for {@link Date} handling.
 *
 * @author qzz
 */
public class DateTypeDescriptor extends AbstractTypeDescriptor<Date> {
    public static final DateTypeDescriptor INSTANCE = new DateTypeDescriptor();
    public static final String DATE_FORMAT = "dd MMMM yyyy";

    public static class DateMutabilityPlan extends MutableMutabilityPlan<Date> {
        public static final DateMutabilityPlan INSTANCE = new DateMutabilityPlan();

        @Override
        public Date deepCopyNotNull(Date value) {
            return new Date(value.getTime());
        }
    }

    public DateTypeDescriptor() {
        super(Date.class, DateMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(Date value) {
        return new SimpleDateFormat(DATE_FORMAT).format(value);
    }

    @Override
    public Date fromString(String string) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(string);
        } catch (ParseException pe) {
            throw new TypeException("could not parse date string" + string, pe);
        }
    }

    @Override
    public boolean areEqual(Date one, Date another) {
        if (one == another) {
            return true;
        }
        return !(one == null || another == null) && one.getTime() == another.getTime();

    }

    @Override
    public int extractHashCode(Date value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        return CalendarTypeDescriptor.INSTANCE.extractHashCode(calendar);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(Date value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (java.sql.Date.class.isAssignableFrom(type)) {
            final java.sql.Date rtn = java.sql.Date.class.isInstance(value)
                    ? (java.sql.Date) value
                    : new java.sql.Date(value.getTime());
            return (X) rtn;
        }
        if (java.sql.Time.class.isAssignableFrom(type)) {
            final java.sql.Time rtn = java.sql.Time.class.isInstance(value)
                    ? (java.sql.Time) value
                    : new java.sql.Time(value.getTime());
            return (X) rtn;
        }
        if (java.sql.Timestamp.class.isAssignableFrom(type)) {
            final java.sql.Timestamp rtn = java.sql.Timestamp.class.isInstance(value)
                    ? (java.sql.Timestamp) value
                    : new java.sql.Timestamp(value.getTime());
            return (X) rtn;
        }
        if (Date.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (Calendar.class.isAssignableFrom(type)) {
            final GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeInMillis(value.getTime());
            return (X) cal;
        }
        if (Long.class.isAssignableFrom(type)) {
            return (X) Long.valueOf(value.getTime());
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Date wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Date.class.isInstance(value)) {
            return (Date) value;
        }

        if (Long.class.isInstance(value)) {
            return new Date((Long) value);
        }

        if (Calendar.class.isInstance(value)) {
            return new Date(((Calendar) value).getTimeInMillis());
        }

        throw unknownWrap(value.getClass());
    }
}
