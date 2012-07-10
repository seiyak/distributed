package distributed.filter.helper;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public class SplitSecondPhase implements Callable {

	private static Logger log = Logger.getLogger( SplitFirstPhase.class );
	private final String input;
	private final int start;
	private final int end;
	private final List<MatchRange> ranges;

	public SplitSecondPhase(String input, int start, int end, List<MatchRange> ranges) {
		this.input = input;
		this.start = start - 1;
		this.end = end - 1;
		this.ranges = ranges;
	}

	public String[] call() throws Exception {

		try {
			return new String[] { input
					.substring( ( ranges.get( start ).getStart() + 1 ), ranges.get( end ).getStart() ) };
		}
		catch ( IndexOutOfBoundsException ex ) {
			log.error( ex );
			return new String[] { input.substring( ( ranges.get( start ).getStart() + 1 ), input.length() ) };
		}
	}

}
