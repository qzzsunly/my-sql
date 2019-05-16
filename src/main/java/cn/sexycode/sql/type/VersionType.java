package cn.sexycode.sql.type;


import java.util.Comparator;

/**
 * Additional contract for types which may be used to version (and optimistic lock) data.
 *
 */
public interface VersionType<T> extends Type {

    /**
     * Get a comparator for version values.
     *
     * @return The comparator to use to compare different version values.
     */
    Comparator<T> getComparator();
}
