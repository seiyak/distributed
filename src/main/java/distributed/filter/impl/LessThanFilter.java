package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.LessThanStrategy;

public class LessThanFilter<I> extends AbstractEachFilterOthers<I> {

	public LessThanFilter(int numberOfFilter, I from) {
		super( numberOfFilter, new LessThanStrategy<I>( from ) );
	}
}
