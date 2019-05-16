package cn.sexycode.sql.type;


import cn.sexycode.sql.type.descriptor.java.ClobTypeDescriptor;

import java.sql.Clob;

/**
 * A type that maps between {@link java.sql.Types#CLOB CLOB} and {@link Clob}
 *
 * @author qzz
 * @author qzz
 */
public class ClobType extends AbstractSingleColumnStandardBasicType<Clob> {
    public static final ClobType INSTANCE = new ClobType();

    public ClobType() {
        super(cn.sexycode.sql.type.descriptor.sql.ClobTypeDescriptor.DEFAULT, ClobTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "clob";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

}
