package cn.sexycode.sql.type.descriptor.sql;

import cn.sexycode.sql.type.descriptor.ValueBinder;
import cn.sexycode.sql.type.descriptor.ValueExtractor;
import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;

import java.io.Serializable;

/**
 * Descriptor for the <tt>SQL</tt>/<tt>JDBC</tt> side of a value mapping.
 * <p/>
 * NOTE : Implementations should be registered with the {@link SqlTypeDescriptor}.  The built-in Hibernate
 * implementations register themselves on construction.
 *
 * @author qzz
 */
public interface SqlTypeDescriptor extends Serializable {
    /**
     * Return the {@linkplain java.sql.Types JDBC type-code} for the column mapped by this type.
     *
     * @return typeCode The JDBC type-code
     */
    public int getSqlType();

    /**
     * Is this descriptor available for remapping?
     *
     * @return {@code true} indicates this descriptor can be remapped; otherwise, {@code false}
     * @see WrapperOptions#remapSqlTypeDescriptor
     * @see org.hibernate.dialect.Dialect#remapSqlTypeDescriptor
     */
    public boolean canBeRemapped();

    /**
     * Get the binder (setting JDBC in-going parameter values) capable of handling values of the type described by the
     * passed descriptor.
     *
     * @param javaTypeDescriptor The descriptor describing the types of Java values to be bound
     * @return The appropriate binder.
     */
    public <X> ValueBinder<X> getBinder(JavaTypeDescriptor<X> javaTypeDescriptor);

    /**
     * Get the extractor (pulling out-going values from JDBC objects) capable of handling values of the type described
     * by the passed descriptor.
     *
     * @param javaTypeDescriptor The descriptor describing the types of Java values to be extracted
     * @return The appropriate extractor
     */
    public <X> ValueExtractor<X> getExtractor(JavaTypeDescriptor<X> javaTypeDescriptor);
}
