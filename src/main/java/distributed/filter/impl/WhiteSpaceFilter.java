package distributed.filter.impl;

import distributed.filter.AbstractEachFilter;

public class WhiteSpaceFilter extends AbstractEachFilter<String> {

	private static final String WHITE_SPACE = "\\s";

	public WhiteSpaceFilter(int numberOfFilter, boolean split) {
		super( WHITE_SPACE, split, numberOfFilter );
	}
}
