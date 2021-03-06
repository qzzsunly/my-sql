package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.TypeException;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

/**
 * Descriptor for {@link Character} handling.
 *
 * @author qzz
 */
public class CharacterTypeDescriptor extends AbstractTypeDescriptor<Character> {
    public static final CharacterTypeDescriptor INSTANCE = new CharacterTypeDescriptor();

    public CharacterTypeDescriptor() {
        super(Character.class);
    }

    @Override
    public String toString(Character value) {
        return value.toString();
    }

    @Override
    public Character fromString(String string) {
        if (string.length() != 1) {
            throw new TypeException("multiple or zero characters found parsing string");
        }
        return string.charAt(0);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(Character value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Character.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        if (Number.class.isAssignableFrom(type)) {
            return (X) Short.valueOf((short) value.charValue());
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Character wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Character.class.isInstance(value)) {
            return (Character) value;
        }
        if (String.class.isInstance(value)) {
            final String str = (String) value;
            return str.charAt(0);
        }
        if (Number.class.isInstance(value)) {
            final Number nbr = (Number) value;
            return (char) nbr.shortValue();
        }
        throw unknownWrap(value.getClass());
    }
}
