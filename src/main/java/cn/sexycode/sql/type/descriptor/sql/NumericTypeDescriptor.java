package cn.sexycode.sql.type.descriptor.sql;

import java.sql.Types;

/**
 * Descriptor for {@link Types#NUMERIC NUMERIC} handling.
 *
 * @author qzz
 */
public class NumericTypeDescriptor extends DecimalTypeDescriptor {
    public static final NumericTypeDescriptor INSTANCE = new NumericTypeDescriptor();

    public NumericTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.NUMERIC;
    }
}
