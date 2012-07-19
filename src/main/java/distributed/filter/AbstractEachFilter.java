package distributed.filter;

import distributed.filter.pattern.FilterPattern;

public abstract class AbstractEachFilter<I, R> implements Filter<I, R> {

	protected final distributed.filter.pattern.FilterPattern filterPattern;
	protected final boolean split;
	protected final int numberOfFilter;

	protected AbstractEachFilter(String patternStr, boolean split, int numberOfFilter){
		filterPattern = new FilterPattern( patternStr );
		this.split = split;
		this.numberOfFilter = numberOfFilter;
	}
}
