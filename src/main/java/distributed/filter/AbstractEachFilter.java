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

	final protected int[] getLoopProperties(int initialNumberOfTasks, I[] input) {

		int[] properties = new int[3];
		if (initialNumberOfTasks == 0) {

			properties[0] = input.length
					/ Runtime.getRuntime().availableProcessors();
			properties[1] = Runtime.getRuntime().availableProcessors();
			properties[2] = input.length % properties[1];

		} else {

			properties[0] = input.length / numberOfFilter;
			properties[1] = numberOfFilter;
			properties[2] = input.length % numberOfFilter;
		}

		return properties;
	}
}
