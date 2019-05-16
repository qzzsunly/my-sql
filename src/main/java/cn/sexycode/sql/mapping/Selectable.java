package cn.sexycode.sql.mapping;

import cn.sexycode.sql.dialect.Dialect;

/**
 * Models the commonality between a column and a formula (computed value).
 */
public interface Selectable {
    public String getAlias(Dialect dialect);

    public String getAlias(Dialect dialect, Table table);

    public boolean isFormula();

    public String getText(Dialect dialect);

    public String getText();
}
