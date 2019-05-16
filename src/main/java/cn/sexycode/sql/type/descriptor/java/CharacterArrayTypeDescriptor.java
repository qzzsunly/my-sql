package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.util.CharacterStream;
import cn.sexycode.sql.util.CharacterStreamImpl;
import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Descriptor for {@code Character[]} handling.
 *
 */
public class CharacterArrayTypeDescriptor extends AbstractTypeDescriptor<Character[]> {
    public static final CharacterArrayTypeDescriptor INSTANCE = new CharacterArrayTypeDescriptor();

    @SuppressWarnings({"unchecked"})
    public CharacterArrayTypeDescriptor() {
        super(Character[].class, ArrayMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(Character[] value) {
        return new String(unwrapChars(value));
    }

    @Override
    public Character[] fromString(String string) {
        return wrapChars(string.toCharArray());
    }

    @Override
    public boolean areEqual(Character[] one, Character[] another) {
        return one == another
                || (one != null && another != null && Arrays.equals(one, another));
    }

    @Override
    public int extractHashCode(Character[] chars) {
        int hashCode = 1;
        for (Character aChar : chars) {
            hashCode = 31 * hashCode + aChar;
        }
        return hashCode;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Comparator<Character[]> getComparator() {
        return IncomparableComparator.INSTANCE;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(Character[] value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Character[].class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) new String(unwrapChars(value));
        }

        if (Reader.class.isAssignableFrom(type)) {
            return (X) new StringReader(new String(unwrapChars(value)));
        }
        if (CharacterStream.class.isAssignableFrom(type)) {
            return (X) new CharacterStreamImpl(new String(unwrapChars(value)));
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Character[] wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Character[].class.isInstance(value)) {
            return (Character[]) value;
        }
        if (String.class.isInstance(value)) {
            return wrapChars(((String) value).toCharArray());
        }
        if (Clob.class.isInstance(value)) {
            return wrapChars(DataHelper.extractString(((Clob) value)).toCharArray());
        }
        if (Reader.class.isInstance(value)) {
            return wrapChars(DataHelper.extractString((Reader) value).toCharArray());
        }
        throw unknownWrap(value.getClass());
    }

    private Character[] wrapChars(char[] chars) {
        if (chars == null) {
            return null;
        }
        final Character[] result = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[i];
        }
        return result;
    }

    private char[] unwrapChars(Character[] chars) {
        if (chars == null) {
            return null;
        }
        final char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[i];
        }
        return result;
    }
}
