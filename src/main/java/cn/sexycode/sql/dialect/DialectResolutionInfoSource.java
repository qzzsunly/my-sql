package cn.sexycode.sql.dialect;

/**
 * Contract for the source of DialectResolutionInfo.
 *
 */
public interface DialectResolutionInfoSource {
    /**
     * Get the DialectResolutionInfo
     *
     * @return The DialectResolutionInfo
     */
    public DialectResolutionInfo getDialectResolutionInfo();
}
