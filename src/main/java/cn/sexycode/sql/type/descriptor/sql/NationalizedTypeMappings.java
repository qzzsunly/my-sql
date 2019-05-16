package cn.sexycode.sql.type.descriptor.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Types;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages a mapping between nationalized and non-nationalized variants of JDBC types.
 * <p>
 * At the moment we only care about being able to map non-nationalized codes to the
 * corresponding nationalized equivalent, so that's all we implement for now
 *
 * @author qzz
 */
public class NationalizedTypeMappings {
    private static final Logger log = LoggerFactory.getLogger(NationalizedTypeMappings.class);

    /**
     * Singleton access
     */
    public static final NationalizedTypeMappings INSTANCE = new NationalizedTypeMappings();

    private final Map<Integer, Integer> nationalizedCodeByNonNationalized;

    public NationalizedTypeMappings() {
        this.nationalizedCodeByNonNationalized = new ConcurrentHashMap<Integer, Integer>();
        map(Types.CHAR, Types.NCHAR);
        map(Types.CLOB, Types.NCLOB);
        map(Types.LONGVARCHAR, Types.LONGNVARCHAR);
        map(Types.VARCHAR, Types.NVARCHAR);
    }

    private void map(int nonNationalizedCode, int nationalizedCode) {
        nationalizedCodeByNonNationalized.put(nonNationalizedCode, nationalizedCode);
    }

    public int getCorrespondingNationalizedCode(int jdbcCode) {
        Integer nationalizedCode = nationalizedCodeByNonNationalized.get(jdbcCode);
        if (nationalizedCode == null) {
            log.debug("Unable to locate nationalized jdbc-code equivalent for given jdbc code : " + jdbcCode);
            return jdbcCode;
        }
        return nationalizedCode;
    }
}
