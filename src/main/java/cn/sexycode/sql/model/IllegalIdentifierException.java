package cn.sexycode.sql.model;

import cn.sexycode.sql.MysqlException;

/**
 * Indicates an attempted use of a name that was deemed illegal
 *
 */
public class IllegalIdentifierException extends MysqlException {
	public IllegalIdentifierException(String s) {
		super( s );
	}
}
