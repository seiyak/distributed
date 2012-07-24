package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.GreaterThanStrategy;

public class GreaterThanFilter<I> extends AbstractEachFilterOthers<I> {

	public GreaterThanFilter(int numberOfFilter, I from) {
		super(numberOfFilter, new GreaterThanStrategy<I>(from));
	}

}
