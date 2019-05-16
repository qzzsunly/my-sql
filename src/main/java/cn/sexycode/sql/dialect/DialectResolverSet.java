package cn.sexycode.sql.dialect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A {@link DialectResolver} implementation which coordinates resolution by delegating to sub-resolvers.
 *
 */
public class DialectResolverSet implements DialectResolver {

    private List<DialectResolver> resolvers;

    public DialectResolverSet() {
        this(new ArrayList<DialectResolver>());
    }

    public DialectResolverSet(List<DialectResolver> resolvers) {
        this.resolvers = resolvers;
    }

    public DialectResolverSet(DialectResolver... resolvers) {
        this(Arrays.asList(resolvers));
    }

    @Override
    public Dialect resolveDialect(DialectResolutionInfo info) {
        for (DialectResolver resolver : resolvers) {
            try {
                final Dialect dialect = resolver.resolveDialect(info);
                if (dialect != null) {
                    return dialect;
                }
            } catch (Exception e) {
            }
        }

        return null;
    }

    /**
     * Add a resolver at the end of the underlying resolver list.  The resolver added by this method is at lower
     * priority than any other existing resolvers.
     *
     * @param resolver The resolver to add.
     */
    public void addResolver(DialectResolver resolver) {
        resolvers.add(resolver);
    }

    /**
     * Add a resolver at the beginning of the underlying resolver list.  The resolver added by this method is at higher
     * priority than any other existing resolvers.
     *
     * @param resolver The resolver to add.
     */
    public void addResolverAtFirst(DialectResolver resolver) {
        resolvers.add(0, resolver);
    }
}
