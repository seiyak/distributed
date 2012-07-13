package distributed.filter.impl;

import distributed.filter.AbstractEachFilter;

public class TabFilter extends AbstractEachFilter<String> {

	private static final String TAB = "\\t";

	public TabFilter(int numberOfFilter, boolean split) {
		super( TAB, split, numberOfFilter );
	}

}
