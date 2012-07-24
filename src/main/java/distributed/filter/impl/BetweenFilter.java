package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.BetweenStrategy;

public class BetweenFilter<I> extends AbstractEachFilterOthers<I> {

	public BetweenFilter(int numberOfFilter, I from, I to) {
		super( numberOfFilter, new BetweenStrategy<I>( from, to ) );
	}
}
