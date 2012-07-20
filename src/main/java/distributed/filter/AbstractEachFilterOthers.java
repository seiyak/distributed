package distributed.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import distributed.filter.strategy.AbstractFilterStrategy;

public abstract class AbstractEachFilterOthers<I> extends
		AbstractEachFilter<I, I> {

	private final AbstractFilterStrategy<I> filterStrategy;

	protected AbstractEachFilterOthers(int numberOfFilter,
			AbstractFilterStrategy<I> filterStrategy) {
		super(null, false, numberOfFilter);

		this.filterStrategy = filterStrategy;
	}

	public List<I> filter(I[] input) throws Exception {

		if (input == null) {
			throw new IllegalArgumentException(
					"input must not be null but found null in filter.");
		} else if (input.length == 0) {
			return Collections.EMPTY_LIST;
		}

		List<I> result = Collections.synchronizedList(new ArrayList<I>());
		int[] loopProperties = getLoopProperties(input.length / numberOfFilter,
				input);

		int i = 0;
		for (i = 0; i < loopProperties[1]; i++) {
			int start = i * loopProperties[0];
			int end = start + loopProperties[0];

			List<I> output = new ArrayList<I>();
			// TODO should the input be split by start and end here ?
			// currently the entire input is copied into each thread.
			result.addAll(new UnSplitLocalFilterOthers<I, List<I>>(input,
					start, end, filterStrategy, output).call());

			if (i == (loopProperties[1] - 1) && loopProperties[2] != 0) {
				if (loopProperties[2] <= loopProperties[1]) {
					int newStart = end;
					int newEnd = newStart + loopProperties[2];

					output.clear();
					result.addAll(new UnSplitLocalFilterOthers<I, List<I>>(
							input, newStart, newEnd, filterStrategy, output)
							.call());
				}
			}
		}

		return Collections.unmodifiableList(result);
	}
}
