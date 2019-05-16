package cn.sexycode.sql.mapping.ordering.antlr;

import cn.sexycode.sql.model.Template;

/**
 * Reference to a formula fragment.
 *
 */
public interface FormulaReference extends SqlValueReference {
	/**
	 * Retrieve the formula fragment.  It is important to note that this is what the persister calls the
	 * "formula template", which has the $PlaceHolder$ (see {@link Template#TEMPLATE})
	 * markers injected.
	 *
	 * @return The formula fragment template.
	 */
	public String getFormulaFragment();
}
