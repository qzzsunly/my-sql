package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.PrimitiveCharacterArrayTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.ClobTypeDescriptor;

/**
 * Map a char[] to a Clob
 *
 */
public class PrimitiveCharacterArrayClobType extends AbstractSingleColumnStandardBasicType<char[]> {
    public static final CharacterArrayClobType INSTANCE = new CharacterArrayClobType();

    public PrimitiveCharacterArrayClobType() {
        super(ClobTypeDescriptor.DEFAULT, PrimitiveCharacterArrayTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        // todo name these annotation types for addition to the registry
        return null;
    }
}
