package cn.sexycode.sql.mapping.ordering.antlr;

/**
 * Reference to a column name.
 *
 */
public interface ColumnReference extends SqlValueReference {
	/**
	 * Retrieve the column name.
	 *
	 * @return THe column name
	 */
	public String getColumnName();
}
