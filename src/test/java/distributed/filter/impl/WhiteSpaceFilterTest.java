package distributed.filter.impl;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WhiteSpaceFilterTest {

	private static Logger log = Logger.getLogger( WhiteSpaceFilterTest.class );
	private WhiteSpaceFilter splitTrueSpaceFilter;
	private WhiteSpaceFilter splitFalseSpaceFilter;

	@Before
	public void setUp() throws Exception {
		splitTrueSpaceFilter = new WhiteSpaceFilter( Runtime.getRuntime().availableProcessors(), true );
		splitFalseSpaceFilter = new WhiteSpaceFilter( Runtime.getRuntime().availableProcessors(), false );
	}

	@Test
	public void testFilterWithoutAnnotationSplitTrue() throws Exception {

		String[] filtered = (String[]) splitTrueSpaceFilter.filter( "this is a sample input." );

		for ( int i = 0; i < filtered.length; i++ ) {
			log.info( "filtered index: " + i + " value: " + filtered[i] );
		}

		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 5 but found size == " + filtered.length, filtered.length == 5 );
		assertEachInStringArray( filtered, "this", "is", "a", "sample", "input." );
	}

	@Test
	public void testFilterWithAnnotationSplitTrue() {

	}

	@Test
	public void testFilterWithoutAnnotationSplitFalse() throws Exception {
		String[] filtered = (String[]) splitFalseSpaceFilter.filter( "this is a sample input." );

		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 4 but found size == " + filtered.length, filtered.length == 4 );
		assertEachInStringArray( filtered, " ", " ", " ", " " );
	}

	private void assertEachInStringArray(String[] target, String... arrayElements) {

		assertTrue( "expecting target size, " + target.length + " == arrayElements size, " + arrayElements.length
				+ " but found target size, " + target.length + " arrayElements size, " + arrayElements.length,
				target.length == arrayElements.length );
		for ( int i = 0; i < target.length; i++ ) {
			assertTrue( "expecting the same string but found target[" + i + "], '" + target[i] + "', arrayElements[" + i
					+ "], '" + arrayElements[i] + "'", target[i].equals( arrayElements[i] ) );
		}
	}

	@After
	public void tearDown() throws Exception {
	}
}
