package cn.sexycode.sql.model;


/**
 */
public class QualifiedNameImpl extends QualifiedNameParser.NameParts implements QualifiedName {
	public QualifiedNameImpl(Namespace.Name schemaName, Identifier objectName) {
		this(
				schemaName.getCatalog(),
				schemaName.getSchema(),
				objectName
		);
	}

	public QualifiedNameImpl(Identifier catalogName, Identifier schemaName, Identifier objectName) {
		super( catalogName, schemaName, objectName );
	}
}
