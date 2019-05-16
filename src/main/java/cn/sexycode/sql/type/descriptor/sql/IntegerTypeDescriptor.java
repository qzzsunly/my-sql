package cn.sexycode.sql.type.descriptor.sql;

import cn.sexycode.sql.type.descriptor.ValueBinder;
import cn.sexycode.sql.type.descriptor.ValueExtractor;
import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;

import java.sql.*;

/**
 * Descriptor for {@link Types#INTEGER INTEGER} handling.
 *
 * @author qzz
 */
public class IntegerTypeDescriptor implements SqlTypeDescriptor {
    public static final IntegerTypeDescriptor INSTANCE = new IntegerTypeDescriptor();

    public IntegerTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.INTEGER;
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
                st.setInt(index, javaTypeDescriptor.unwrap(value, Integer.class, options));
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
                    throws SQLException {
                st.setInt(name, javaTypeDescriptor.unwrap(value, Integer.class, options));
            }
        };
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicExtractor<X>(javaTypeDescriptor, this) {
            @Override
            protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(rs.getInt(name), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getInt(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getInt(name), options);
            }
        };
    }
}
