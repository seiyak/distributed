package distributed.filter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import distributed.filter.AbstractFilter;
import distributed.filter.UnSplitLocalFilter;
import distributed.filter.helper.MatchRange;
import distributed.filter.helper.SplitFirstPhase;
import distributed.filter.helper.SplitSecondPhase;

public class WhiteSpaceFilter extends AbstractFilter<String> {

	private static Logger log = Logger.getLogger( WhiteSpaceFilter.class );
	private final int numberOfCPUs = Runtime.getRuntime().availableProcessors();
	private static final String WHITE_SPACE = "\\s";

	public WhiteSpaceFilter(boolean split) {
		super( WHITE_SPACE, split );
	}

	public String[] filter(String input) throws Exception {

		if ( input == null ) {
			throw new IllegalArgumentException( "input must not be null but found null in filter." );
		}

		if ( input.equals( "" ) ) {
			return new String[] {};
		}

		List<String> l = Collections.synchronizedList( new ArrayList<String>() );
		final int numberOfTask = input.length() / numberOfCPUs;
		final int leftOver = input.length() % numberOfCPUs;

		if ( split ) {
			return split( input, numberOfTask, leftOver, l );
		}
		else {
			int i = 0;
			for ( i = 0; i < numberOfCPUs; i++ ) {
				int start = i * numberOfTask;
				int end = start + numberOfTask;

				List<String> output = Collections.synchronizedList( new ArrayList<String>() );
				l.addAll( new UnSplitLocalFilter<List<String>>( input, start, end, filterPattern, output ).call() );

				if ( i == ( numberOfCPUs - 1 ) && leftOver != 0 ) {
					if ( leftOver <= numberOfCPUs ) {
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
		for ( i = 0; i < numberOfCPUs; i++ ) {

			int start = i * numberOfTask;
			int end = start + numberOfTask;

			List<MatchRange> output = new ArrayList<MatchRange>();
			result.addAll( new SplitFirstPhase<List<MatchRange>>( input, start, end, filterPattern, output ).call() );

			if ( i == ( numberOfCPUs - 1 ) && leftOver != 0 ) {
				if ( leftOver <= numberOfCPUs ) {
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

			final int numberOfTasks = ranges.size() / numberOfCPUs;
			final int leftOver = numberOfTasks % numberOfCPUs;
			int i = 0;
			for ( i = 1; i < numberOfCPUs; i++ ) {
				int start = i * numberOfTasks;
				int end = start + numberOfTasks;

				System.arraycopy( new SplitSecondPhase( input, start, end, ranges ).call(), 0, result, start, 1 );
				if ( i == ( numberOfCPUs - 1 ) && leftOver != 0 ) {
					if ( leftOver <= numberOfCPUs ) {
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
