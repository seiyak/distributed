package distributed.filter.impl;

import distributed.filter.AbstractEachFilterString;

public class NewLineFilter extends AbstractEachFilterString {

	private static final String NEWLINE = "\\n";

	public NewLineFilter(boolean split, int numberOfFilter) {
		super( NEWLINE, split, numberOfFilter );
	}
}
