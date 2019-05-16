package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.CharacterTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.NCharTypeDescriptor;

import java.io.Serializable;

/**
 * A type that maps between {@link java.sql.Types#NCHAR NCHAR(1)} and {@link Character}
 *
 */
public class CharacterNCharType
        extends AbstractSingleColumnStandardBasicType<Character>
        implements PrimitiveType<Character>, DiscriminatorType<Character> {

    public static final CharacterNCharType INSTANCE = new CharacterNCharType();

    public CharacterNCharType() {
        super(NCharTypeDescriptor.INSTANCE, CharacterTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "ncharacter";
    }

    @Override
    public Serializable getDefaultValue() {
        throw new UnsupportedOperationException("not a valid id type");
    }

    @Override
    public Class getPrimitiveClass() {
        return char.class;
    }

    @Override
    public String objectToSQLString(Character value, Dialect dialect) {
        return '\'' + toString(value) + '\'';
    }

    @Override
    public Character stringToObject(String xml) {
        return fromString(xml);
    }

}
