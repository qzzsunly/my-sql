package cn.sexycode.sql.type.descriptor.java;


import cn.sexycode.sql.type.descriptor.WrapperOptions;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Descriptor for the Java side of a value mapping.
 *
 * @author qzz
 */
public interface JavaTypeDescriptor<T> extends Serializable {
    /**
     * Retrieve the Java type handled here.
     *
     * @return The Java type.
     */
    Class<T> getJavaTypeClass();

    /**
     * Retrieve the mutability plan for this Java type.
     *
     * @return The mutability plan
     */
    MutabilityPlan<T> getMutabilityPlan();

    /**
     * Retrieve the natural comparator for this type.
     *
     * @return The natural comparator.
     */
    Comparator<T> getComparator();

    /**
     * Extract a proper hash code for this value.
     *
     * @param value The value for which to extract a hash code.
     * @return The extracted hash code.
     */
    int extractHashCode(T value);

    /**
     * Determine if two instances are equal
     *
     * @param one     One instance
     * @param another The other instance
     * @return True if the two are considered equal; false otherwise.
     */
    boolean areEqual(T one, T another);

    /**
     * Extract a loggable representation of the value.
     *
     * @param value The value for which to extract a loggable representation.
     * @return The loggable representation
     */
    String extractLoggableRepresentation(T value);

    String toString(T value);

    T fromString(String string);

    /**
     * Unwrap an instance of our handled Java type into the requested type.
     * <p/>
     * As an example, if this is a {@code JavaTypeDescriptor<Integer>} and we are asked to unwrap
     * the {@code Integer value} as a {@code Long} we would return something like
     * <code>Long.valueOf( value.longValue() )</code>.
     * <p/>
     * Intended use is during {@link java.sql.PreparedStatement} binding.
     *
     * @param value   The value to unwrap
     * @param type    The type as which to unwrap
     * @param options The options
     * @param <X>     The conversion type.
     * @return The unwrapped value.
     */
    <X> X unwrap(T value, Class<X> type, WrapperOptions options);

    /**
     * Wrap a value as our handled Java type.
     * <p/>
     * Intended use is during {@link java.sql.ResultSet} extraction.
     *
     * @param value   The value to wrap.
     * @param options The options
     * @param <X>     The conversion type.
     * @return The wrapped value.
     */
    <X> T wrap(X value, WrapperOptions options);
}
