package cn.sexycode.sql.type;


import cn.sexycode.sql.type.descriptor.java.StringTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.LongNVarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#LONGNVARCHAR LONGNVARCHAR} and {@link String}
 *
 */
public class NTextType extends AbstractSingleColumnStandardBasicType<String> {
    public static final NTextType INSTANCE = new NTextType();

    public NTextType() {
        super(LongNVarcharTypeDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "ntext";
    }

}
