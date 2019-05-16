package cn.sexycode.sql.model;


/**
 */
public class QualifiedTableName extends QualifiedNameImpl {
	public QualifiedTableName(Identifier catalogName, Identifier schemaName, Identifier tableName) {
		super( catalogName, schemaName, tableName );
	}

	public QualifiedTableName(Namespace.Name schemaName, Identifier tableName) {
		super( schemaName, tableName );
	}

	public Identifier getTableName() {
		return getObjectName();
	}
}
