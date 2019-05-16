package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.CalendarTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.TimestampTypeDescriptor;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * A type that maps between {@link java.sql.Types#TIMESTAMP TIMESTAMP} and {@link Calendar}
 *
 */
public class CalendarType
        extends AbstractSingleColumnStandardBasicType<Calendar>
        implements VersionType<Calendar> {

    public static final CalendarType INSTANCE = new CalendarType();

    public CalendarType() {
        super(TimestampTypeDescriptor.INSTANCE, CalendarTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "calendar";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), Calendar.class.getName(), GregorianCalendar.class.getName()};
    }

    @Override
    public Comparator<Calendar> getComparator() {
        return getJavaTypeDescriptor().getComparator();
    }
}
