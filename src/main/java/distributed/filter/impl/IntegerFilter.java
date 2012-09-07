package distributed.filter.impl;

import java.util.List;

import distributed.filter.AbstractEachFilterCast;

public class IntegerFilter extends AbstractEachFilterCast<Integer> {

	private static final String INTEGER = "\\d+";
	private static final Integer instance = new Integer( 0 );

	public IntegerFilter(int numberOfFilter) {
		super( INTEGER, false, numberOfFilter );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List filter(String[] input) throws Exception {

		return doFilter( input, instance );
	}
}
