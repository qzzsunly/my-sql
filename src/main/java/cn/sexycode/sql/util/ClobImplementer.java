package cn.sexycode.sql.util;

/**
 * Marker interface for non-contextually created {@link java.sql.Clob} instances..
 *
 */
public interface ClobImplementer {
    /**
     * Gets access to the data underlying this CLOB.
     *
     * @return Access to the underlying data.
     */
    CharacterStream getUnderlyingStream();
}
