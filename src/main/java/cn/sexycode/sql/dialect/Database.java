package cn.sexycode.sql.dialect;


/**
 * List all supported relational database systems.
 *
 */
public enum Database {

    MYSQL {
        @Override
        public Class<? extends Dialect> latestDialect() {
            return MySQL8Dialect.class;
        }

        @Override
        public Dialect resolveDialect(DialectResolutionInfo info) {
            final String databaseName = info.getDatabaseName();

            if ("MySQL".equals(databaseName)) {
                final int majorVersion = info.getDatabaseMajorVersion();
                final int minorVersion = info.getDatabaseMinorVersion();

                if (majorVersion < 5) {
                    return new MySQLDialect();
                } else if (majorVersion == 5) {
                    if (minorVersion < 5) {
                        return new MySQL5Dialect();
                    } else if (minorVersion < 7) {
                        return new MySQL55Dialect();
                    } else {
                        return new MySQL57Dialect();
                    }
                }

                return latestDialectInstance(this);
            }

            return null;
        }
    };

    public abstract Class<? extends Dialect> latestDialect();

    public abstract Dialect resolveDialect(DialectResolutionInfo info);

    private static Dialect latestDialectInstance(Database database) {
        try {
            return database.latestDialect().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DialectException(e);
        }
    }
}
