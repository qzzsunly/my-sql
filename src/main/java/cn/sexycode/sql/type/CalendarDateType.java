package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.CalendarDateTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.DateTypeDescriptor;

import java.util.Calendar;

/**
 * A type mapping {@link java.sql.Types#DATE DATE} and {@link Calendar}
 *
 */
public class CalendarDateType extends AbstractSingleColumnStandardBasicType<Calendar> {
    public static final CalendarDateType INSTANCE = new CalendarDateType();

    public CalendarDateType() {
        super(DateTypeDescriptor.INSTANCE, CalendarDateTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "calendar_date";
    }

}
