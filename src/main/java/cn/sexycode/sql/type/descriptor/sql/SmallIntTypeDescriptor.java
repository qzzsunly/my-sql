package cn.sexycode.sql.type.descriptor.sql;


import cn.sexycode.sql.type.descriptor.ValueBinder;
import cn.sexycode.sql.type.descriptor.ValueExtractor;
import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;

import java.sql.*;

/**
 * Descriptor for {@link Types#SMALLINT SMALLINT} handling.
 *
 * @author qzz
 */
public class SmallIntTypeDescriptor implements SqlTypeDescriptor {
    public static final SmallIntTypeDescriptor INSTANCE = new SmallIntTypeDescriptor();

    public SmallIntTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.SMALLINT;
    }

    @Override
    public boolean canBeRemapped() {
        return true;
    }

    @Override
    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicBinder<X>(javaTypeDescriptor, this) {
            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                st.setShort(index, javaTypeDescriptor.unwrap(value, Short.class, options));
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
                    throws SQLException {
                st.setShort(name, javaTypeDescriptor.unwrap(value, Short.class, options));
            }
        };
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicExtractor<X>(javaTypeDescriptor, this) {
            @Override
            protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(rs.getShort(name), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getShort(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getShort(name), options);
            }
        };
    }
}
