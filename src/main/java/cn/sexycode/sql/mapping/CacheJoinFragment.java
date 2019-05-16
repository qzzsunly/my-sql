package cn.sexycode.sql.mapping;

import cn.sexycode.util.core.exception.AssertionFailure;

/**
 * A Cach&eacute; dialect join.  Differs from ANSI only in that full outer join
 * is not supported.
 *
 */
public class CacheJoinFragment extends ANSIJoinFragment {

	@Override
    public void addJoin(String rhsTableName, String rhsAlias, String[] lhsColumns, String[] rhsColumns, JoinType joinType, String on) {
		if ( joinType == JoinType.FULL_JOIN ) {
			throw new AssertionFailure( "Cache does not support full outer joins" );
		}
		super.addJoin( rhsTableName, rhsAlias, lhsColumns, rhsColumns, joinType, on );
	}

}
