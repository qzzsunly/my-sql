package cn.sexycode.sql.mapping;

import java.util.Map;

/**
 * An ANSI SQL CASE expression : {@code case when ... then ... end as ..}
 *
 */
public class ANSICaseFragment extends CaseFragment {

	@Override
	public String toFragmentString() {
		
		final StringBuilder buf = new StringBuilder( cases.size() * 15 + 10 )
			.append("case");

		for ( Object o : cases.entrySet() ) {
			Map.Entry me = (Map.Entry) o;
			buf.append( " when " )
					.append( me.getKey() )
					.append( " is not null then " )
					.append( me.getValue() );
		}
		
		buf.append(" end");

		if (returnColumnName!=null) {
			buf.append(" as ")
				.append(returnColumnName);
		}

		return buf.toString();
	}
	
}
