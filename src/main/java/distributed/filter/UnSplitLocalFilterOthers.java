package distributed.filter;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import distributed.filter.strategy.AbstractFilterStrategy;

public class UnSplitLocalFilterOthers<I, R extends List<I>> implements
		Callable<R> {

	private static Logger log = Logger
			.getLogger(UnSplitLocalFilterOthers.class);
	private I[] input;
	private final int start;
	private final int end;
	private final AbstractFilterStrategy<I> filterStrategy;
	private final R output;

	public UnSplitLocalFilterOthers(I[] input, int start, int end,
			AbstractFilterStrategy<I> filterStrategy, R output) {

		this.input = input;
		this.start = start;
		this.end = end;
		this.filterStrategy = filterStrategy;
		this.output = output;
	}

	public R call() throws Exception {
		for (int i = start; i < end; i++) {
			if (filterStrategy.compare(input[i])) {
				output.add(input[i]);
			}
		}

		return output;
	}

}
