package distributed.filter;

import distributed.filter.pattern.FilterPattern;

public abstract class AbstractFilter<T extends Object> implements Filter<T> {
	
	protected final distributed.filter.pattern.FilterPattern filterPattern;
	protected final boolean split;
	
	protected AbstractFilter(String patternStr, boolean split){
		filterPattern = new FilterPattern( patternStr );
		this.split = split;
	}
}
