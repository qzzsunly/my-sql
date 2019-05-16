package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.BlobTypeDescriptor;

import java.sql.Blob;

/**
 * A type that maps between {@link java.sql.Types#BLOB BLOB} and {@link Blob}
 *
 * @author qzz
 * @author qzz
 */
public class BlobType extends AbstractSingleColumnStandardBasicType<Blob> {
    public static final BlobType INSTANCE = new BlobType();

    public BlobType() {
        super(cn.sexycode.sql.type.descriptor.sql.BlobTypeDescriptor.DEFAULT, BlobTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "blob";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }


}
