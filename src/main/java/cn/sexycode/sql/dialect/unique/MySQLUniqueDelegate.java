package cn.sexycode.sql.dialect.unique;

import cn.sexycode.sql.dialect.Dialect;

/**
 */
public class MySQLUniqueDelegate extends DefaultUniqueDelegate {

    /**
     * Constructs MySQLUniqueDelegate
     *
     * @param dialect The dialect for which we are handling unique constraints
     */
    public MySQLUniqueDelegate(Dialect dialect) {
        super(dialect);
    }

    @Override
    protected String getDropUnique() {
        return " drop index ";
    }
}
