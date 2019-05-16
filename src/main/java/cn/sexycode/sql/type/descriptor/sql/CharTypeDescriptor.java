package cn.sexycode.sql.type.descriptor.sql;

import java.sql.Types;

/**
 * Descriptor for {@link Types#CHAR CHAR} handling.
 *
 * @author qzz
 */
public class CharTypeDescriptor extends VarcharTypeDescriptor {
    public static final CharTypeDescriptor INSTANCE = new CharTypeDescriptor();

    public CharTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.CHAR;
    }
}
