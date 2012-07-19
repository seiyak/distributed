package distributed.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import distributed.filter.helper.MatchRange;
import distributed.filter.helper.SplitFirstPhase;
import distributed.filter.helper.SplitSecondPhase;

public abstract class AbstractEachFilterString extends
		AbstractEachFilter<String, String> {

	protected AbstractEachFilterString(String patternStr, boolean split,
			int numberOfFilter) {
		super(patternStr, split, numberOfFilter);
	}

	public List<String> filter(String[] input) throws Exception {

		List<String> result = null;
		for (String str : input) {
			result = doFilter(str);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<String> doFilter(String input) throws Exception {

		if (input == null) {
			throw new IllegalArgumentException(
					"input must not be null but found null in filter.");
		}

		if (input.equals("")) {
			return Collections.EMPTY_LIST;
		}

		List<String> l = Collections.synchronizedList(new ArrayList<String>());
		final int numberOfTask = input.length() / numberOfFilter;
		final int leftOver = input.length() % numberOfFilter;

		if (split) {
			return Arrays.asList(split(input, numberOfTask, leftOver, l));
		} else {
			int i = 0;
			for (i = 0; i < numberOfFilter; i++) {
				int start = i * numberOfTask;
				int end = start + numberOfTask;

				List<String> output = Collections
						.synchronizedList(new ArrayList<String>());
				l.addAll(new UnSplitLocalFilter<List<String>>(input, start,
						end, filterPattern, output).call());

				if (i == (numberOfFilter - 1) && leftOver != 0) {
					if (leftOver <= numberOfFilter) {
						int newStart = end;
						int newEnd = newStart + leftOver;

						output.clear();
						l.addAll(new UnSplitLocalFilter<List<String>>(input,
								newStart, newEnd, filterPattern, output).call());
					}
				}
			}
		}

		return l;
	}

	private String[] split(String input, final int numberOfTask,
			final int leftOver, List<String> finalResult) throws Exception {

		return runSplitSecondPhase(
				runSplitFirstPhase(input, numberOfTask, leftOver), input);
	}

	private List<MatchRange> runSplitFirstPhase(String input,
			final int numberOfTask, final int leftOver) throws Exception {

		List<MatchRange> result = Collections
				.synchronizedList(new ArrayList<MatchRange>());

		int i = 0;
		for (i = 0; i < numberOfFilter; i++) {

			int start = i * numberOfTask;
			int end = start + numberOfTask;

			List<MatchRange> output = new ArrayList<MatchRange>();
			result.addAll(new SplitFirstPhase<List<MatchRange>>(input, start,
					end, filterPattern, output).call());

			if (i == (numberOfFilter - 1) && leftOver != 0) {
				if (leftOver <= numberOfFilter) {
					int newStart = end;
					int newEnd = newStart + leftOver;

					output.clear();
					result.addAll(new SplitFirstPhase<List<MatchRange>>(input,
							newStart, newEnd, filterPattern, output).call());
				}
			}
		}

		return result;
	}

	private String[] runSplitSecondPhase(List<MatchRange> ranges, String input)
			throws Exception {

		if (ranges.size() == 0) {
			return new String[] {};
		}

		String[] result = null;

		if (ranges.size() == 1) {
			if (ranges.get(0).getEnd() == (input.length() - 1)) {
				result = new String[1];
				result[0] = input.substring(0, ranges.get(0).getStart());
			} else {
				result = new String[2];
				result[0] = input.substring(0, ranges.get(0).getStart());
				result[1] = input.substring(ranges.get(0).getEnd(),
						input.length());
			}
		} else if (numberOfFilter > 1 && ranges.size() > 1) {
			result = new String[ranges.size() + 1];
			result[0] = input.substring(0, ranges.get(0).getStart());

			final int numberOfTasks = (ranges.size() / numberOfFilter) == 0 ? 1
					: (ranges.size() / numberOfFilter);
			final int leftOver = numberOfTasks % numberOfFilter;
			final int numberOfLoops = numberOfTasks == 1 ? ranges.size() + 1
					: numberOfFilter;
			int i = 0;
			for (i = 1; i < numberOfLoops; i++) {
				int start = i * numberOfTasks;
				int end = start + numberOfTasks;

				System.arraycopy(
						new SplitSecondPhase(input, start, end, ranges).call(),
						0, result, start, 1);
				if (i == (numberOfFilter - 1) && leftOver != 0) {
					if (leftOver <= numberOfFilter) {
						int newStart = end;
						int newEnd = end + leftOver;

						do {
							System.arraycopy(new SplitSecondPhase(input,
									newStart, newEnd, ranges).call(), 0,
									result, newStart, 1);
							newStart = newEnd;
							newEnd = newStart + leftOver;
						} while (newStart != (ranges.size() + 1));
					}
				}
			}
		} else if (numberOfFilter == 1 && ranges.size() > 1) {
			result = input.split(filterPattern.getPattern());
		}

		return result;
	}
}
