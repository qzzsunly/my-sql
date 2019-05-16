package cn.sexycode.sql.type;

import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.LocalDateJavaDescriptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 */
public class LocalDateType
        extends AbstractSingleColumnStandardBasicType<LocalDate>
        implements LiteralType<LocalDate> {

    /**
     * Singleton access
     */
    public static final LocalDateType INSTANCE = new LocalDateType();

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    public LocalDateType() {
        super(cn.sexycode.sql.type.descriptor.sql.DateTypeDescriptor.INSTANCE, LocalDateJavaDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return LocalDate.class.getSimpleName();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

    @Override
    public String objectToSQLString(LocalDate value, Dialect dialect) throws Exception {
        return "{d '" + FORMATTER.format(value) + "'}";
    }
}
