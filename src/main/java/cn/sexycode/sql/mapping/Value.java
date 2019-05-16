package cn.sexycode.sql.mapping;

import cn.sexycode.sql.type.Mapping;
import cn.sexycode.sql.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A value is anything that is persisted by value, instead of
 * by reference. It is essentially a Hibernate Type, together
 * with zero or more columns. Values are wrapped by things with
 * higher level semantics, for example properties, collections,
 * classes.
 *
 */
public interface Value extends Serializable {
    public int getColumnSpan();

    public Iterator<Selectable> getColumnIterator();

    public Type getType() throws MappingException;


    public Table getTable();

    public boolean hasFormula();

    public boolean isAlternateUniqueKey();

    public boolean isNullable();

    public boolean[] getColumnUpdateability();

    public boolean[] getColumnInsertability();

    public void createForeignKey() throws MappingException;

    public boolean isSimpleValue();

    public boolean isValid(Mapping mapping) throws MappingException;

    public void setTypeUsingReflection(String className, String propertyName) throws MappingException;

    public Object accept(ValueVisitor visitor);

}
