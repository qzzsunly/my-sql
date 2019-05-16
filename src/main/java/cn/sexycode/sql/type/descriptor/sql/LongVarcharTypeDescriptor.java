package cn.sexycode.sql.type.descriptor.sql;

import java.sql.Types;

/**
 * Descriptor for {@link Types#LONGVARCHAR LONGVARCHAR} handling.
 *
 * @author qzz
 */
public class LongVarcharTypeDescriptor extends VarcharTypeDescriptor {
    public static final LongVarcharTypeDescriptor INSTANCE = new LongVarcharTypeDescriptor();

    public LongVarcharTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.LONGVARCHAR;
    }
}
