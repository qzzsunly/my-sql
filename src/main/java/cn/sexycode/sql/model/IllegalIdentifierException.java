package cn.sexycode.sql.model;

import cn.sexycode.sql.MySqlException;

/**
 * Indicates an attempted use of a name that was deemed illegal
 *
 */
public class IllegalIdentifierException extends MySqlException {
	public IllegalIdentifierException(String s) {
		super( s );
	}
}
