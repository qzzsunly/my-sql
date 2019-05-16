package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.PrimitiveByteArrayTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.LongVarbinaryTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#LONGVARBINARY LONGVARBINARY} and {@code byte[]}
 *
 */
public class ImageType extends AbstractSingleColumnStandardBasicType<byte[]> {
    public static final ImageType INSTANCE = new ImageType();

    public ImageType() {
        super(LongVarbinaryTypeDescriptor.INSTANCE, PrimitiveByteArrayTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "image";
    }
}
