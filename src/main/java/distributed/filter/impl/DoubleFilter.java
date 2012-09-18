package distributed.filter.impl;

import java.util.List;

import distributed.filter.AbstractEachFilterCast;

public class DoubleFilter extends AbstractEachFilterCast<Double> {

	private static final String DOUBLE = "\\d+\\.\\d+";
	private static final Double instance = new Double( 0 );

	public DoubleFilter(int numberOfFilter) {
		super( DOUBLE, false, numberOfFilter );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List filter(String[] input) throws Exception {

		return doFilter( input, instance );
	}
}
