package cn.sexycode.sql.type;

import cn.sexycode.sql.type.descriptor.java.ClassTypeDescriptor;
import cn.sexycode.sql.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#VARCHAR VARCHAR} and {@link Class}
 *
 * @author qzz
 * @author qzz
 */
public class ClassType extends AbstractSingleColumnStandardBasicType<Class> {
    public static final ClassType INSTANCE = new ClassType();

    public ClassType() {
        super(VarcharTypeDescriptor.INSTANCE, ClassTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "class";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

}
