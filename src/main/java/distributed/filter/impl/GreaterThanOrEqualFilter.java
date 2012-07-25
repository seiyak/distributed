package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.GreaterThanOrEqualStrategy;

public class GreaterThanOrEqualFilter<I> extends AbstractEachFilterOthers<I> {

	public GreaterThanOrEqualFilter(int numberOfFilter, I from) {
		super( numberOfFilter, new GreaterThanOrEqualStrategy<I>( from ) );
	}

}
