package distributed.filter.helper;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import distributed.filter.pattern.FilterPattern;

public class SplitFirstPhase<T extends List> implements Callable<T> {

	private static Logger log = Logger.getLogger( SplitFirstPhase.class );
	private final String input;
	private final int start;
	private final int end;
	private final FilterPattern filterPattern;
	private final T output;

	public SplitFirstPhase(String input, int start, int end, FilterPattern filterPattern, T output) {
		this.input = input;
		this.start = start;
		this.end = end;
		this.filterPattern = filterPattern;
		this.output = output;
	}

	public T call() throws Exception {
		
		String str = input.substring( start, filterPattern.getTerminateCharacterIndexFrom( input, end ) );
		List<MatchRange> ranges = filterPattern.getMatchRange( str, start );
		if ( !ranges.isEmpty() ) {
			output.addAll( ranges );
		}

		return output;
	}
}
