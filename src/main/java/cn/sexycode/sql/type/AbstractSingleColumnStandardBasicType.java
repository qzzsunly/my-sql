package cn.sexycode.sql.type;


import cn.sexycode.sql.type.descriptor.java.JavaTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.SqlTypeDescriptor;

/**
 * TODO : javadoc
 *
 */
public abstract class AbstractSingleColumnStandardBasicType<T>
        extends AbstractStandardBasicType<T>
        implements SingleColumnType<T> {

    public AbstractSingleColumnStandardBasicType(SqlTypeDescriptor sqlTypeDescriptor, JavaTypeDescriptor<T> javaTypeDescriptor) {
        super(sqlTypeDescriptor, javaTypeDescriptor);
    }

    @Override
    public final int sqlType() {
        return getSqlTypeDescriptor().getSqlType();
    }

}
