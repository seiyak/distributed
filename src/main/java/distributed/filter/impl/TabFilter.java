package distributed.filter.impl;

import distributed.filter.AbstractEachFilterString;

public class TabFilter extends AbstractEachFilterString {

	private static final String TAB = "\\t";

	public TabFilter(int numberOfFilter, boolean split) {
		super( TAB, split, numberOfFilter );
	}

}
