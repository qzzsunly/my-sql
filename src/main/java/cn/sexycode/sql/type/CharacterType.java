package cn.sexycode.sql.type;


import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.descriptor.java.CharacterTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.CharTypeDescriptor;

import java.io.Serializable;

/**
 * A type that maps between {@link java.sql.Types#CHAR CHAR(1)} and {@link Character}
 *
 * @author qzz
 * @author qzz
 */
public class CharacterType
        extends AbstractSingleColumnStandardBasicType<Character>
        implements PrimitiveType<Character>, DiscriminatorType<Character> {

    public static final CharacterType INSTANCE = new CharacterType();

    public CharacterType() {
        super(CharTypeDescriptor.INSTANCE, CharacterTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "character";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), char.class.getName(), Character.class.getName()};
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
