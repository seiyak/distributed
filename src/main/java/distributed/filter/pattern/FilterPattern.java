package distributed.filter.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import distributed.filter.helper.MatchRange;

public class FilterPattern implements FilterPatternFinder {

	private final java.util.regex.Pattern pattern;

	public FilterPattern(String patternStr) {
		if (patternStr == null || patternStr.equals("")) {
			pattern = null;
		} else {
			pattern = java.util.regex.Pattern.compile(patternStr);
		}
	}

	public String[] find(String str) {
		Matcher matcher = pattern.matcher( str );

		List<String> res = new ArrayList<String>();
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
		List<MatchRange> ranges = new ArrayList<MatchRange>();
		while ( matcher.find() ) {

			MatchRange range = new MatchRange( matcher.start() + offset, matcher.end() + offset );
			ranges.add( range );
		}

		return ranges;
	}
}
