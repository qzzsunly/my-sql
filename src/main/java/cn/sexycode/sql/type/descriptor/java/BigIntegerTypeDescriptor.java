package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Descriptor for {@link BigInteger} handling.
 *
 * @author qzz
 */
public class BigIntegerTypeDescriptor extends AbstractTypeDescriptor<BigInteger> {
    public static final BigIntegerTypeDescriptor INSTANCE = new BigIntegerTypeDescriptor();

    public BigIntegerTypeDescriptor() {
        super(BigInteger.class);
    }

    @Override
    public String toString(BigInteger value) {
        return value.toString();
    }

    @Override
    public BigInteger fromString(String string) {
        return new BigInteger(string);
    }

    @Override
    public int extractHashCode(BigInteger value) {
        return value.intValue();
    }

    @Override
    public boolean areEqual(BigInteger one, BigInteger another) {
        return one == another || (one != null && another != null && one.compareTo(another) == 0);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <X> X unwrap(BigInteger value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (BigInteger.class.isAssignableFrom(type)) {
            return (X) value;
        }
        if (BigDecimal.class.isAssignableFrom(type)) {
            return (X) new BigDecimal(value);
        }
        if (Byte.class.isAssignableFrom(type)) {
            return (X) Byte.valueOf(value.byteValue());
        }
        if (Short.class.isAssignableFrom(type)) {
            return (X) Short.valueOf(value.shortValue());
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
        throw unknownUnwrap(type);
    }

    @Override
    public <X> BigInteger wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (BigInteger.class.isInstance(value)) {
            return (BigInteger) value;
        }
        if (BigDecimal.class.isInstance(value)) {
            return ((BigDecimal) value).toBigIntegerExact();
        }
        if (Number.class.isInstance(value)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        throw unknownWrap(value.getClass());
    }
}
