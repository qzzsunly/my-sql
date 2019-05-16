package cn.sexycode.sql.dialect;

/**
 * Contract for determining the {@link Dialect} to use based on information about the database / driver.
 *
 */
public interface DialectResolver  {
    /**
     * Determine the {@link Dialect} to use based on the given information.  Implementations are expected to return
     * the {@link Dialect} instance to use, or {@code null} if the they did not locate a match.
     *
     * @param info Access to the information about the database/driver needed to perform the resolution
     * @return The dialect to use, or null.
     */
    Dialect resolveDialect(DialectResolutionInfo info);
}
