package cn.sexycode.sql.type.descriptor.sql;


import cn.sexycode.sql.type.descriptor.ValueBinder;
import cn.sexycode.sql.type.descriptor.ValueExtractor;
import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;

import java.sql.*;

/**
 * Descriptor for {@link Types#VARBINARY VARBINARY} handling.
 *
 * @author qzz
 */
public class VarbinaryTypeDescriptor implements SqlTypeDescriptor {
    public static final VarbinaryTypeDescriptor INSTANCE = new VarbinaryTypeDescriptor();

    public VarbinaryTypeDescriptor() {
    }

    public int getSqlType() {
        return Types.VARBINARY;
    }

    @Override
    public boolean canBeRemapped() {
        return true;
    }

    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicBinder<X>(javaTypeDescriptor, this) {

            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                st.setBytes(index, javaTypeDescriptor.unwrap(value, byte[].class, options));
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
                    throws SQLException {
                st.setBytes(name, javaTypeDescriptor.unwrap(value, byte[].class, options));
            }
        };
    }

    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicExtractor<X>(javaTypeDescriptor, this) {
            @Override
            protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(rs.getBytes(name), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getBytes(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getBytes(name), options);
            }
        };
    }
}
