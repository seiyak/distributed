package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.LessThanOrEqualStrategy;

public class LessThanOrEqualFilter<I> extends AbstractEachFilterOthers<I> {

	public LessThanOrEqualFilter(int numberOfFilter, I from) {
		super( numberOfFilter, new LessThanOrEqualStrategy<I>( from ) );
	}
}
