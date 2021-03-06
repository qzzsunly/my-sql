package cn.sexycode.sql.type;

import java.util.LinkedHashSet;

/**
 * A specialization of the set type, with (resultset-based) ordering.
 */
public class OrderedSetType extends SetType {

    public OrderedSetType(TypeFactory.TypeScope typeScope, String role, String propertyRef) {
        super(typeScope, role, propertyRef);
    }

    @Override
    public Object instantiate(int anticipatedSize) {
        return anticipatedSize > 0
                ? new LinkedHashSet(anticipatedSize)
                : new LinkedHashSet();
    }

}
