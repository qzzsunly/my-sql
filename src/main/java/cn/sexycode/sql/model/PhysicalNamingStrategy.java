package cn.sexycode.sql.model;

import cn.sexycode.sql.Environment;

/**
 * Pluggable strategy contract for applying physical naming rules for database object names.
 *
 * NOTE: Ideally we'd pass "extra" things in here like Dialect, etc to better handle identifier
 * length constraints or auto quoting of identifiers.  However, the pre-metamodel model does not
 * necessarily know this information at the time the strategy is called.
 *
 */
public interface PhysicalNamingStrategy {
	 Identifier toPhysicalCatalogName(Identifier name, Environment Environment);

	 Identifier toPhysicalSchemaName(Identifier name, Environment Environment);

	 Identifier toPhysicalTableName(Identifier name, Environment Environment);

	 Identifier toPhysicalSequenceName(Identifier name, Environment Environment);

	 Identifier toPhysicalColumnName(Identifier name, Environment Environment);
}
