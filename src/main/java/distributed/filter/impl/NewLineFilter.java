package distributed.filter.impl;

import distributed.filter.AbstractEachFilter;

public class NewLineFilter extends AbstractEachFilter<String> {

	private static final String NEWLINE = "\\n";

	public NewLineFilter(int numberOfFilter, boolean split) {
		super( NEWLINE, split, numberOfFilter );
	}
}
