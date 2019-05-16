package cn.sexycode.sql.model;


/**
 */
public class QualifiedSequenceName extends QualifiedNameImpl {
	public QualifiedSequenceName(Identifier catalogName, Identifier schemaName, Identifier sequenceName) {
		super( catalogName, schemaName, sequenceName );
	}

	public QualifiedSequenceName(Namespace.Name schemaName, Identifier sequenceName) {
		super( schemaName, sequenceName );
	}

	public Identifier getSequenceName() {
		return getObjectName();
	}
}
