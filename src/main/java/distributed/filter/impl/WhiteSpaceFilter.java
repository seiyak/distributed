package distributed.filter.impl;

import distributed.filter.AbstractEachFilterString;

public class WhiteSpaceFilter extends AbstractEachFilterString {

	private static final String WHITE_SPACE = "\\s";

	public WhiteSpaceFilter(int numberOfFilter, boolean split) {
		super( WHITE_SPACE, split, numberOfFilter );
	}
}
