package cn.sexycode.sql.type;


import cn.sexycode.sql.type.descriptor.java.PrimitiveCharacterArrayTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.NClobTypeDescriptor;

/**
 * Map a char[] to a NClob
 *
 */
public class PrimitiveCharacterArrayNClobType extends AbstractSingleColumnStandardBasicType<char[]> {
    public static final CharacterArrayClobType INSTANCE = new CharacterArrayClobType();

    public PrimitiveCharacterArrayNClobType() {
        super(NClobTypeDescriptor.DEFAULT, PrimitiveCharacterArrayTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        // todo name these annotation types for addition to the registry
        return null;
    }
}
