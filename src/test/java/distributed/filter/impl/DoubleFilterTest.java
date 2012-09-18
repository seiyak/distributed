package distributed.filter.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DoubleFilterTest {

	private DoubleFilter doubleFilter;

	@Before
	public void setUp() throws Exception {

		doubleFilter = new DoubleFilter( Runtime.getRuntime().availableProcessors() );
	}

	@Test
	public void testFilterWithoutAnnotation() throws Exception {

		List<Double> res = doubleFilter
				.filter( new String[] { "The temperature yesterday was 70.0F and today it's 65.3F. It dropped 4.7F." } );
		checkEachElement( res, 70.0, 65.3, 4.7 );

	}

	@Test
	public void testFilterWithAnnotation() throws Exception {

		DoubleFilterAnnotated filtered = new DoubleFilterAnnotated();
		List<Double> resultList = filtered.runFilter();
		checkEachElement( resultList, 99.3, 100.00 );
	}

	private void checkEachElement(List<Double> res, double... expected) {

		assertNotNull( "expecting res != null but found " + res, res );
		assertTrue( "execpting the same size but found res.size() == " + res.size() + " expected.length == "
				+ expected.length, res.size() == expected.length );

		for ( int i = 0; i < res.size(); i++ ) {
			assertTrue( "expecting the same value but found res.get(" + i + ") == " + res.get( i ) + " expected[" + i
					+ "] == " + expected[i], res.get( i ) == expected[i] );
		}
	}
}
