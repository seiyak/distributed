package distributed.filter.impl.integer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.impl.IntegerFilter;

public class IntegerFilterTest {

	private IntegerFilter integerFilter;

	@Before
	public void setUp() throws Exception {

		integerFilter = new IntegerFilter( Runtime.getRuntime().availableProcessors() );
	}

	@Test
	public void testFilterWithoutAnnotation() throws Exception {

		List<Integer> res = integerFilter.filter( new String[] { "About 100 people were there at least 2 times" } );
		checkEachElement( res, new int[] { 100, 2 } );

		res = integerFilter.filter( new String[] { "Weren't there people there ?" } );
		checkEachElement( res, new int[] {} );

		res = integerFilter.filter( new String[] { "they had 20 cookies and 5 cokes and also 1000 pizzas" } );
		checkEachElement( res, new int[] { 20, 5, 1000 } );

		res = integerFilter.filter( new String[] {} );
		checkEachElement( res, new int[] {} );

		res = integerFilter.filter( new String[] { "There are no numbers in this sentence." } );
		checkEachElement( res, new int[] {} );
	}

	@Test
	public void testFilterWithAnnotation() throws Exception {

		IntegerFilterAnnotated filtered = new IntegerFilterAnnotated();
		List<Integer> resultList = filtered.runFilter();
		checkEachElement( resultList, 2, 3 );
	}

	private void checkEachElement(List<Integer> res, int... expected) {

		assertNotNull( "expecting res != null but found " + res, res );
		assertTrue( "execpting the same size but found res.size() == " + res.size() + " expected.length == "
				+ expected.length, res.size() == expected.length );

		for ( int i = 0; i < res.size(); i++ ) {
			assertTrue( "expecting the same value but found res.get(" + i + ") == " + res.get( i ) + " expected[" + i
					+ "] == " + expected[i], res.get( i ) == expected[i] );
		}
	}
}
