package cn.sexycode.sql;


public class MySqlException extends RuntimeException {

        /** 
         * Constructs a new <code>PersistenceException</code> exception 
         * with <code>null</code> as its detail message.
         */
	public MySqlException() {
		super();
	}

        /** 
         * Constructs a new <code>PersistenceException</code> exception 
         * with the specified detail message.
         * @param   message   the detail message.
         */
	public MySqlException(String message) {
		super(message);
	}

        /** 
         * Constructs a new <code>PersistenceException</code> exception 
         * with the specified detail message and cause.
         * @param   message   the detail message.
         * @param   cause     the cause.
         */
	public MySqlException(String message, Throwable cause) {
		super(message, cause);
	}
	
        /** 
         * Constructs a new <code>PersistenceException</code> exception 
         * with the specified cause.
         * @param   cause     the cause.
         */
	public MySqlException(Throwable cause) {
		super(cause);
	}
}

