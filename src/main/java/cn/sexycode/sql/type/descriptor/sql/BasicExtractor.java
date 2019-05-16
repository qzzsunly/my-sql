package cn.sexycode.sql.type.descriptor.sql;

import cn.sexycode.sql.type.descriptor.JdbcTypeNameMapper;
import cn.sexycode.sql.type.descriptor.ValueExtractor;
import cn.sexycode.sql.type.descriptor.WrapperOptions;
import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Convenience base implementation of {@link ValueExtractor}
 *
 * @author qzz
 */
public abstract class BasicExtractor<J> implements ValueExtractor<J> {
    private static final Logger log = LoggerFactory.getLogger(BasicExtractor.class);

    private final JavaTypeDescriptor<J> javaDescriptor;
    private final SqlTypeDescriptor sqlDescriptor;

    public BasicExtractor(JavaTypeDescriptor<J> javaDescriptor, SqlTypeDescriptor sqlDescriptor) {
        this.javaDescriptor = javaDescriptor;
        this.sqlDescriptor = sqlDescriptor;
    }

    public JavaTypeDescriptor<J> getJavaDescriptor() {
        return javaDescriptor;
    }

    public SqlTypeDescriptor getSqlDescriptor() {
        return sqlDescriptor;
    }

    @Override
    public J extract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
        final J value = doExtract(rs, name, options);
        final boolean traceEnabled = log.isTraceEnabled();
        if (value == null || rs.wasNull()) {
            if (traceEnabled) {
                log.trace(
                        "extracted value ([%s] : [%s]) - [null]",
                        name,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType())
                );
            }
            return null;
        } else {
            if (traceEnabled) {
                log.trace(
                        "extracted value ([%s] : [%s]) - [%s]",
                        name,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType()),
                        getJavaDescriptor().extractLoggableRepresentation(value)
                );
            }
            return value;
        }
    }

    /**
     * Perform the extraction.
     * <p/>
     * Called from {@link #extract}.  Null checking of the value (as well as consulting {@link ResultSet#wasNull}) is
     * done there.
     *
     * @param rs      The result set
     * @param name    The value name in the result set
     * @param options The binding options
     * @return The extracted value.
     * @throws SQLException Indicates a problem access the result set
     */
    protected abstract J doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException;

    @Override
    public J extract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
        final J value = doExtract(statement, index, options);
        final boolean traceEnabled = log.isTraceEnabled();
        if (value == null || statement.wasNull()) {
            if (traceEnabled) {
                log.trace(
                        "extracted procedure output  parameter ([%s] : [%s]) - [null]",
                        index,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType())
                );
            }
            return null;
        } else {
            if (traceEnabled) {
                log.trace(
                        "extracted procedure output  parameter ([%s] : [%s]) - [%s]",
                        index,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType()),
                        getJavaDescriptor().extractLoggableRepresentation(value)
                );
            }
            return value;
        }
    }

    /**
     * Perform the extraction.
     * <p/>
     * Called from {@link #extract}.  Null checking of the value (as well as consulting {@link ResultSet#wasNull}) is
     * done there.
     *
     * @param statement The callable statement containing the output parameter
     * @param index     The index (position) of the output parameter
     * @param options   The binding options
     * @return The extracted value.
     * @throws SQLException Indicates a problem accessing the parameter value
     */
    protected abstract J doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException;

    @Override
    public J extract(CallableStatement statement, String[] paramNames, WrapperOptions options) throws SQLException {
        if (paramNames.length > 1) {
            throw new IllegalArgumentException("Basic value extraction cannot handle multiple output parameters");
        }
        final String paramName = paramNames[0];
        final J value = doExtract(statement, paramName, options);
        final boolean traceEnabled = log.isTraceEnabled();
        if (value == null || statement.wasNull()) {
            if (traceEnabled) {
                log.trace(
                        "extracted named procedure output  parameter ([%s] : [%s]) - [null]",
                        paramName,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType())
                );
            }
            return null;
        } else {
            if (traceEnabled) {
                log.trace(
                        "extracted named procedure output  parameter ([%s] : [%s]) - [%s]",
                        paramName,
                        JdbcTypeNameMapper.getTypeName(getSqlDescriptor().getSqlType()),
                        getJavaDescriptor().extractLoggableRepresentation(value)
                );
            }
            return value;
        }
    }

    /**
     * Perform the extraction.
     * <p/>
     * Called from {@link #extract}.  Null checking of the value (as well as consulting {@link ResultSet#wasNull}) is
     * done there.
     *
     * @param statement The callable statement containing the output parameter
     * @param name      The output parameter name
     * @param options   The binding options
     * @return The extracted value.
     * @throws SQLException Indicates a problem accessing the parameter value
     */
    protected abstract J doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException;
}
