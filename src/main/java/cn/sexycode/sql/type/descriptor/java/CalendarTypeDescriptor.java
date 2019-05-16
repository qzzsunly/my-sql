package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.util.CalendarComparator;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Descriptor for {@link Calendar} handling.
 *
 * @author qzz
 */
public class CalendarTypeDescriptor extends AbstractTypeDescriptor<Calendar> {
    public static final CalendarTypeDescriptor INSTANCE = new CalendarTypeDescriptor();

    public static class CalendarMutabilityPlan extends MutableMutabilityPlan<Calendar> {
        public static final CalendarMutabilityPlan INSTANCE = new CalendarMutabilityPlan();

        public Calendar deepCopyNotNull(Calendar value) {
            return (Calendar) value.clone();
        }
    }

    protected CalendarTypeDescriptor() {
        super(Calendar.class, CalendarMutabilityPlan.INSTANCE);
    }

    public String toString(Calendar value) {
        return DateTypeDescriptor.INSTANCE.toString(value.getTime());
    }

    public Calendar fromString(String string) {
        Calendar result = new GregorianCalendar();
        result.setTime(DateTypeDescriptor.INSTANCE.fromString(string));
        return result;
    }

    @Override
    public boolean areEqual(Calendar one, Calendar another) {
        if (one == another) {
            return true;
        }
        if (one == null || another == null) {
            return false;
        }

        return one.get(Calendar.MILLISECOND) == another.get(Calendar.MILLISECOND)
                && one.get(Calendar.SECOND) == another.get(Calendar.SECOND)
                && one.get(Calendar.MINUTE) == another.get(Calendar.MINUTE)
                && one.get(Calendar.HOUR_OF_DAY) == another.get(Calendar.HOUR_OF_DAY)
                && one.get(Calendar.DAY_OF_MONTH) == another.get(Calendar.DAY_OF_MONTH)
                && one.get(Calendar.MONTH) == another.get(Calendar.MONTH)
                && one.get(Calendar.YEAR) == another.get(Calendar.YEAR);
    }

    @Override
    public int extractHashCode(Calendar value) {
        int hashCode = 1;
        hashCode = 31 * hashCode + value.get(Calendar.MILLISECOND);
        hashCode = 31 * hashCode + value.get(Calendar.SECOND);
        hashCode = 31 * hashCode + value.get(Calendar.MINUTE);
        hashCode = 31 * hashCode + value.get(Calendar.HOUR_OF_DAY);
        hashCode = 31 * hashCode + value.get(Calendar.DAY_OF_MONTH);
        hashCode = 31 * hashCode + value.get(Calendar.MONTH);
        hashCode = 31 * hashCode + value.get(Calendar.YEAR);
        return hashCode;
    }

    @Override
    public Comparator<Calendar> getComparator() {
        return CalendarComparator.INSTANCE;
    }

    @SuppressWarnings({"unchecked"})
    public <X> X unwrap(Calendar value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Calendar.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (java.sql.Date.class.isAssignableFrom(type)) {
            return (X) new java.sql.Date(value.getTimeInMillis());
        }
        if (java.sql.Time.class.isAssignableFrom(type)) {
            return (X) new java.sql.Time(value.getTimeInMillis());
        }
        if (java.sql.Timestamp.class.isAssignableFrom(type)) {
            return (X) new java.sql.Timestamp(value.getTimeInMillis());
        }
        if (Date.class.isAssignableFrom(type)) {
            return (X) new Date(value.getTimeInMillis());
        }
        throw unknownUnwrap(type);
    }

    public <X> Calendar wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Calendar.class.isInstance(value)) {
            return (Calendar) value;
        }

        if (!Date.class.isInstance(value)) {
            throw unknownWrap(value.getClass());
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime((Date) value);

        return cal;
    }
}
