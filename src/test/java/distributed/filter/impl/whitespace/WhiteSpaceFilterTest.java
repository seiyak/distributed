package distributed.filter.impl.whitespace;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import distributed.filter.impl.WhiteSpaceFilter;

public class WhiteSpaceFilterTest {

	private static Logger log = Logger.getLogger( WhiteSpaceFilterTest.class );
	private WhiteSpaceFilter splitTrueSpaceFilter;
	private WhiteSpaceFilter splitFalseSpaceFilter;

	@Before
	public void setUp() throws Exception {
		splitTrueSpaceFilter = new WhiteSpaceFilter( true, Runtime.getRuntime().availableProcessors() );
		splitFalseSpaceFilter = new WhiteSpaceFilter( false, Runtime.getRuntime().availableProcessors() );
	}

	@Test
	public void testFilterWithoutAnnotationSplitTrue() throws Exception {

		List<String> result = splitTrueSpaceFilter
				.filter(new String[] { "this is a sample input." });
		String[] filtered = result.toArray(new String[result.size()]);

		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 5 but found size == " + filtered.length, filtered.length == 5 );
		assertEachInStringArray( filtered, "this", "is", "a", "sample", "input." );
	}

	@Test
	public void testFilterWithoutAnnotationSplitFalse() throws Exception {

		List<String> result = splitFalseSpaceFilter
				.filter(new String[] { "this is a sample input." });
		String[] filtered = result.toArray(new String[result.size()]);

		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 4 but found size == " + filtered.length, filtered.length == 4 );
		assertEachInStringArray( filtered, " ", " ", " ", " " );
	}

	@Test
	public void testFilterWithAnnotationSplitTrue() throws Exception {

		WhiteSpaceFilteredSplitTrue filtered = new WhiteSpaceFilteredSplitTrue();
		List<String> resultList = filtered.runFilter();
		String[] result = resultList.toArray(new String[resultList.size()]);

		assertNotNull( "expecting result != null but found null", result );
		assertTrue( "expecting rseult.length == 5 but found " + result.length, result.length == 5 );
		assertEachInStringArray( result, "Annotated", "filter", "example", "is", "here." );
	}

	@Test
	public void testFilerWithAnnotationSplitFalse() throws Exception {
		WhiteSpaceFilteredSplitFalse filtered = new WhiteSpaceFilteredSplitFalse();
		List<String> resultList = filtered.runFilter();
		String[] result = resultList.toArray(new String[resultList.size()]);

		assertNotNull( "expecting result != null but found null", result );
		assertTrue( "expecting rseult.length == 4 but found " + result.length, result.length == 4 );
		assertEachInStringArray( result, " ", " ", " ", " " );
	}

	private void assertEachInStringArray(String[] target, String... arrayElements) {

		assertTrue( "expecting target size, " + target.length + " == arrayElements size, " + arrayElements.length
				+ " but found target size, " + target.length + " arrayElements size, " + arrayElements.length,
				target.length == arrayElements.length );
		for ( int i = 0; i < target.length; i++ ) {
			assertTrue( "expecting the same string but found target[" + i + "], '" + target[i] + "', arrayElements["
					+ i + "], '" + arrayElements[i] + "'", target[i].equals( arrayElements[i] ) );
		}
	}

	@After
	public void tearDown() throws Exception {
	}
}
