package cn.sexycode.sql.type.descriptor.sql;

import java.sql.Types;

/**
 * Descriptor for {@link Types#BINARY BINARY} handling.
 *
 * @author qzz
 */
public class BinaryTypeDescriptor extends VarbinaryTypeDescriptor {
    public static final BinaryTypeDescriptor INSTANCE = new BinaryTypeDescriptor();

    public BinaryTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.BINARY;
    }
}
