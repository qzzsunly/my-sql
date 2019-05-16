package cn.sexycode.sql.type.descriptor.sql;

import java.sql.Types;

/**
 * Descriptor for {@link Types#NCHAR NCHAR} handling.
 *
 * @author qzz
 */
public class NCharTypeDescriptor extends NVarcharTypeDescriptor {
    public static final NCharTypeDescriptor INSTANCE = new NCharTypeDescriptor();

    public NCharTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.NCHAR;
    }
}
