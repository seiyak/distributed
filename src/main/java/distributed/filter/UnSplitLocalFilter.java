package distributed.filter;

import java.util.List;
import java.util.concurrent.Callable;

import distributed.filter.pattern.FilterPattern;

public class UnSplitLocalFilter<T extends List> implements Callable<T> {

	private final String input;
	private final int start;
	private final int end;
	private final FilterPattern pattern;
	private final T output;

	public UnSplitLocalFilter(String input, int start, int end, FilterPattern pattern, T output) {

		this.input = input;
		this.start = start;
		this.end = end;
		this.pattern = pattern;
		this.output = output;
	}

	public T call() throws Exception {

		String subStr = input.substring( start, end );
		String[] each = null;

		each = pattern.find( subStr );
		for ( String eachFound : each ) {
			output.add( eachFound );
		}

		return output;
	}

}
