package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.StringTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.LongVarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#LONGVARCHAR LONGVARCHAR} and {@link String}
 *
 * @author qzz,
 */
public class TextType extends AbstractSingleColumnStandardBasicType<String> {
    public static final TextType INSTANCE = new TextType();

    public TextType() {
        super(LongVarcharTypeDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "text";
    }

}
