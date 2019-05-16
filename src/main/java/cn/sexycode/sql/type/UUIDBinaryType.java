package cn.sexycode.sql.type;


import cn.sexycode.sql.type.descriptor.java.UUIDTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.BinaryTypeDescriptor;

import java.util.UUID;

/**
 * A type mapping {@link java.sql.Types#BINARY} and {@link UUID}
 *
 */
public class UUIDBinaryType extends AbstractSingleColumnStandardBasicType<UUID> {
    public static final UUIDBinaryType INSTANCE = new UUIDBinaryType();

    public UUIDBinaryType() {
        super(BinaryTypeDescriptor.INSTANCE, UUIDTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "uuid-binary";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
