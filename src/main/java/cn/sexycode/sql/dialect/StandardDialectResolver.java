package cn.sexycode.sql.dialect;

/**
 * The standard DialectResolver implementation
 *
 */
public final class StandardDialectResolver implements DialectResolver {

    @Override
    public Dialect resolveDialect(DialectResolutionInfo info) {

        for (Database database : Database.values()) {
            Dialect dialect = database.resolveDialect(info);
            if (dialect != null) {
                return dialect;
            }
        }

        return null;
    }
}
