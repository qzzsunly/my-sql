package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.descriptor.WrapperOptions;

/**
 * Descriptor for {@link Short} handling.
 *
 * @author qzz
 */
public class ShortTypeDescriptor extends AbstractTypeDescriptor<Short> {
    public static final ShortTypeDescriptor INSTANCE = new ShortTypeDescriptor();

    public ShortTypeDescriptor() {
        super(Short.class);
    }

    @Override
    public String toString(Short value) {
        return value == null ? null : value.toString();
    }

    @Override
    public Short fromString(String string) {
        return Short.valueOf(string);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(Short value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Short.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (Byte.class.isAssignableFrom(type)) {
            return (X) Byte.valueOf(value.byteValue());
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (X) Integer.valueOf(value.intValue());
        }
        if (Long.class.isAssignableFrom(type)) {
            return (X) Long.valueOf(value.longValue());
        }
        if (Double.class.isAssignableFrom(type)) {
            return (X) Double.valueOf(value.doubleValue());
        }
        if (Float.class.isAssignableFrom(type)) {
            return (X) Float.valueOf(value.floatValue());
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Short wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Short.class.isInstance(value)) {
            return (Short) value;
        }
        if (Number.class.isInstance(value)) {
            return ((Number) value).shortValue();
        }
        if (String.class.isInstance(value)) {
            return Short.valueOf(((String) value));
        }
        throw unknownWrap(value.getClass());
    }
}
