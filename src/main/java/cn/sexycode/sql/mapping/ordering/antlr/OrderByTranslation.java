package cn.sexycode.sql.mapping.ordering.antlr;

/**
 * Represents the result of an order-by translation by {@link @OrderByTranslator}
 *
 */
public interface OrderByTranslation {
	/**
	 * Inject table aliases into the translated fragment to properly qualify column references, using
	 * the given 'aliasResolver' to determine the the proper table alias to use for each column reference.
	 *
	 * @param aliasResolver The strategy to resolver the proper table alias to use per column
	 *
	 * @return The fully translated and replaced fragment.
	 */
	public String injectAliases(OrderByAliasResolver aliasResolver);
}
