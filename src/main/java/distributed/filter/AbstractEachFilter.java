package distributed.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import distributed.filter.helper.MatchRange;
import distributed.filter.helper.SplitFirstPhase;
import distributed.filter.helper.SplitSecondPhase;
import distributed.filter.pattern.FilterPattern;

public abstract class AbstractEachFilter<T extends Object> implements Filter<T> {
	
	protected final distributed.filter.pattern.FilterPattern filterPattern;
	protected final boolean split;
	private final int numberOfFilter;
	
	protected AbstractEachFilter(String patternStr, boolean split, int numberOfFilter){
		filterPattern = new FilterPattern( patternStr );
		this.split = split;
		this.numberOfFilter = numberOfFilter;
	}
	
	public String[] filter(String input) throws Exception {

		if ( input == null ) {
			throw new IllegalArgumentException( "input must not be null but found null in filter." );
		}

		if ( input.equals( "" ) ) {
			return new String[] {};
		}

		List<String> l = Collections.synchronizedList( new ArrayList<String>() );
		final int numberOfTask = input.length() / numberOfFilter;
		final int leftOver = input.length() % numberOfFilter;

		if ( split ) {
			return split( input, numberOfTask, leftOver, l );
		}
		else {
			int i = 0;
			for ( i = 0; i < numberOfFilter; i++ ) {
				int start = i * numberOfTask;
				int end = start + numberOfTask;

				List<String> output = Collections.synchronizedList( new ArrayList<String>() );
				l.addAll( new UnSplitLocalFilter<List<String>>( input, start, end, filterPattern, output ).call() );

				if ( i == ( numberOfFilter - 1 ) && leftOver != 0 ) {
					if ( leftOver <= numberOfFilter ) {
						int newStart = end;
						int newEnd = newStart + leftOver;

						output.clear();
						l.addAll( new UnSplitLocalFilter<List<String>>( input, newStart, newEnd, filterPattern, output )
								.call() );
					}
				}
			}
		}

		return l.toArray( new String[l.size()] );
	}

	private String[] split(String input, final int numberOfTask, final int leftOver, List<String> finalResult)
			throws Exception {

		return runSplitSecondPhase( runSplitFirstPhase( input, numberOfTask, leftOver ), input );
	}

	private List<MatchRange> runSplitFirstPhase(String input, final int numberOfTask, final int leftOver)
			throws Exception {

		List<MatchRange> result = Collections.synchronizedList( new ArrayList<MatchRange>() );

		int i = 0;
		for ( i = 0; i < numberOfFilter; i++ ) {

			int start = i * numberOfTask;
			int end = start + numberOfTask;

			List<MatchRange> output = new ArrayList<MatchRange>();
			result.addAll( new SplitFirstPhase<List<MatchRange>>( input, start, end, filterPattern, output ).call() );

			if ( i == ( numberOfFilter - 1 ) && leftOver != 0 ) {
				if ( leftOver <= numberOfFilter ) {
					int newStart = end;
					int newEnd = newStart + leftOver;

					output.clear();
					result.addAll( new SplitFirstPhase<List<MatchRange>>( input, newStart, newEnd, filterPattern,
							output ).call() );
				}
			}
		}

		return result;
	}

	private String[] runSplitSecondPhase(List<MatchRange> ranges, String input) throws Exception {

		if ( ranges.size() == 0 ) {
			return new String[] {};
		}

		String[] result = null;

		if ( ranges.size() == 1 ) {
			result = new String[1];
			result[0] = input.substring( 0, ranges.get( 0 ).getStart() );
		}
		else if ( ranges.size() > 1 ) {
			result = new String[ranges.size() + 1];
			result[0] = input.substring( 0, ranges.get( 0 ).getStart() );

			final int numberOfTasks = ranges.size() / numberOfFilter;
			final int leftOver = numberOfTasks % numberOfFilter;
			int i = 0;
			for ( i = 1; i < numberOfFilter; i++ ) {
				int start = i * numberOfTasks;
				int end = start + numberOfTasks;

				System.arraycopy( new SplitSecondPhase( input, start, end, ranges ).call(), 0, result, start, 1 );
				if ( i == ( numberOfFilter - 1 ) && leftOver != 0 ) {
					if ( leftOver <= numberOfFilter ) {
						int newStart = end;
						int newEnd = end + leftOver;

						System.arraycopy( new SplitSecondPhase( input, newStart, newEnd, ranges ).call(), 0, result,
								newStart, 1 );
					}
				}
			}
		}

		return result;
	}
}