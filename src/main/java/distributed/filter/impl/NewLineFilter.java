package distributed.filter.impl;

import distributed.filter.AbstractEachFilterString;

public class NewLineFilter extends AbstractEachFilterString {

	private static final String NEWLINE = "\\n";

	public NewLineFilter(int numberOfFilter, boolean split) {
		super( NEWLINE, split, numberOfFilter );
	}
}
