package cn.sexycode.sql.dialect;

import cn.sexycode.sql.MysqlException;
import cn.sexycode.util.core.factory.selector.StrategySelector;
import cn.sexycode.util.core.str.StringUtils;

/**
 * Standard implementation of the {@link DialectFactory} service.
 *
 */
public class DialectFactoryImpl implements DialectFactory {
    private DialectResolver dialectResolver;

    private StrategySelector strategySelector;

    public DialectFactoryImpl(DialectResolver dialectResolver, StrategySelector strategySelector) {
        this.dialectResolver = dialectResolver;
        this.strategySelector = strategySelector;
    }

    /**
     * Intended only for use from testing.
     *
     * @param dialectResolver The DialectResolver to use
     */
    public void setDialectResolver(DialectResolver dialectResolver) {
        this.dialectResolver = dialectResolver;
    }

    @Override
    public Dialect buildDialect(String dialect, DialectResolutionInfoSource resolutionInfoSource) {
        if (!isEmpty(dialect)) {
            return constructDialect(dialect);
        }
        return determineDialect(resolutionInfoSource);

    }

    private Dialect constructDialect(Object dialectReference) {
        final Dialect dialect;
        try {
            dialect = strategySelector.resolveStrategy(Dialect.class, dialectReference);
            if (dialect == null) {
                throw new MysqlException("Unable to construct requested dialect [" + dialectReference + "]");
            }
            return dialect;
        } catch (MysqlException e) {
            throw e;
        } catch (Exception e) {
            throw new MysqlException("Unable to construct requested dialect [" + dialectReference + "]", e);
        }
    }
    @SuppressWarnings("SimplifiableIfStatement")
    private boolean isEmpty(Object dialectReference) {
        if (dialectReference != null) {
            // the referenced value is not null
            if (dialectReference instanceof String) {
                // if it is a String, it might still be empty though...
                return StringUtils.isEmpty((String) dialectReference);
            }
            return false;
        }
        return true;
    }


    /**
     * Determine the appropriate Dialect to use given the connection.
     *
     * @param resolutionInfoSource Access to DialectResolutionInfo used to resolve the Dialect.
     * @return The appropriate dialect instance.
     */
    private Dialect determineDialect(DialectResolutionInfoSource resolutionInfoSource) {
        if (resolutionInfoSource == null) {
//			throw new HibernateException( "Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set" );
        }

        final DialectResolutionInfo info = resolutionInfoSource.getDialectResolutionInfo();
        final Dialect dialect = dialectResolver.resolveDialect(info);

        if (dialect == null) {
//			throw new HibernateException(
//					"Unable to determine Dialect to use [name=" + info.getDatabaseName() +
//							", majorVersion=" + info.getDatabaseMajorVersion() +
//							"]; user must register resolver or explicitly set 'hibernate.dialect'"
//			);
        }

        return dialect;
    }
}
