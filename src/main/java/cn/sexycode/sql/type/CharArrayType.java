package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.PrimitiveCharacterArrayTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#VARCHAR VARCHAR} and {@code char[]}
 *
 * @author qzz
 * @author qzz
 */
public class CharArrayType extends AbstractSingleColumnStandardBasicType<char[]> {
    public static final CharArrayType INSTANCE = new CharArrayType();

    public CharArrayType() {
        super(VarcharTypeDescriptor.INSTANCE, PrimitiveCharacterArrayTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "characters";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), "char[]", char[].class.getName()};
    }
}
