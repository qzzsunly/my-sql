package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.PrimitiveByteArrayTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.BlobTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#BLOB BLOB} and {@code byte[]}
 *
 */
public class MaterializedBlobType extends AbstractSingleColumnStandardBasicType<byte[]> {
    public static final MaterializedBlobType INSTANCE = new MaterializedBlobType();

    public MaterializedBlobType() {
        super(BlobTypeDescriptor.DEFAULT, PrimitiveByteArrayTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "materialized_blob";
    }
}
