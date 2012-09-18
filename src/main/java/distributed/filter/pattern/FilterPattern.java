package distributed.filter.pattern;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import distributed.filter.helper.MatchRange;
import distributed.filter.impl.WhiteSpaceFilter;

public class FilterPattern implements FilterPatternFinder {

	private final java.util.regex.Pattern pattern;
	private static final java.util.regex.Pattern whiteSpacePattern = java.util.regex.Pattern
			.compile( WhiteSpaceFilter.WHITE_SPACE );
	private static final int TAB_ASCII_CODE = 9;
	private static final int LINEFEED_ASCII_CODE = 10;
	private static final int CARRIAGERETURN_ASCII_CODE = 13;
	private static final int SPACE_ASCII_CODE = 32;

	public FilterPattern(String patternStr) {
		if (patternStr == null || patternStr.equals("")) {
			pattern = null;
		} else {
			pattern = java.util.regex.Pattern.compile(patternStr);
		}
	}

	public String[] find(String str) {
		Matcher matcher = pattern.matcher( str );

		List<String> res = new LinkedList<String>();
		while ( matcher.find() ) {
			res.add( matcher.group() );
		}

		return res.toArray( new String[res.size()] );
	}

	public String findFirstOf(String str) {
		Matcher matcher = pattern.matcher( str );

		while ( matcher.find() ) {
			return matcher.group();
		}

		return "";
	}

	public String getPattern() {
		return pattern.pattern();
	}

	public List<MatchRange> getMatchRange(String str, int offset) {

		Matcher matcher = pattern.matcher( str );
		List<MatchRange> ranges = new LinkedList<MatchRange>();
		while ( matcher.find() ) {

			MatchRange range = new MatchRange( matcher.start() + offset, matcher.end() + offset );
			ranges.add( range );
		}

		return ranges;
	}

	public int getTerminateCharacterIndexFrom(String str, int endIndex) {

		if ( str.length() <= endIndex ) {
			return str.length() - 1;
		}

		if ( !isTerminateCharacter( str.charAt( endIndex ) ) ) {
			return getTerminateIndexOf( str, endIndex, false );
		}

		return endIndex;
	}

	private boolean isTerminateCharacter(char character) {

		return ( character == TAB_ASCII_CODE || character == LINEFEED_ASCII_CODE
				|| character == CARRIAGERETURN_ASCII_CODE || character == SPACE_ASCII_CODE );
	}

	private int getTerminateIndexOf(String str, int startIndex, boolean found) {

		int endIndex = 0;

		if ( !found ) {
			try {
				int index = getFirstIndexOf( whiteSpacePattern.matcher( str.substring( startIndex, startIndex + 5 ) ) );
				if(index == 1){
					found = true;
					endIndex = startIndex;
				}else if ( index != -1 ) {
					found = true;
					endIndex = startIndex + index;
				}
				else if ( index == -1 ) {
					startIndex += 5;
					endIndex = getTerminateIndexOf( str, startIndex, found );
				}
			}
			catch ( StringIndexOutOfBoundsException ex ) {
				found = true;
				return startIndex;
			}
		}

		return endIndex;
	}

	private int getFirstIndexOf(Matcher matcher) {

		while ( matcher.find() ) {

			return matcher.start();
		}

		return -1;
	}
}
