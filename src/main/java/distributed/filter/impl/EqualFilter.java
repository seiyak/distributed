package distributed.filter.impl;

import distributed.filter.AbstractEachFilterOthers;
import distributed.filter.strategy.EqualStrategy;

public class EqualFilter<I> extends AbstractEachFilterOthers<I> {

	public EqualFilter(int numberOfFilter, I eq) {
		super(numberOfFilter, new EqualStrategy<I>(eq));
	}
}
