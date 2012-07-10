package distributed.filter;

import distributed.filter.pattern.FilterPattern;

public abstract class AbstractEachFilter<T extends Object> implements Filter<T> {
	
	protected final distributed.filter.pattern.FilterPattern filterPattern;
	protected final boolean split;
	
	protected AbstractEachFilter(String patternStr, boolean split){
		filterPattern = new FilterPattern( patternStr );
		this.split = split;
	}
}
