package cn.sexycode.sql.model;

import cn.sexycode.sql.Environment;

import java.io.Serializable;

/**
 * Standard implementation of the PhysicalNamingStrategy contract.
 *
 */
public class PhysicalNamingStrategyStandardImpl implements PhysicalNamingStrategy, Serializable {
	/**
	 * Singleton access
	 */
	public static final PhysicalNamingStrategyStandardImpl INSTANCE = new PhysicalNamingStrategyStandardImpl();

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, Environment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, Environment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, Environment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, Environment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, Environment context) {
		return name;
	}
}
