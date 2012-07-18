package distributed.filter.impl.tab;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import distributed.filter.impl.TabFilter;

public class TabFilterTest {

	private TabFilter splitTrueTabFilter;
	private TabFilter splitFalseTabFilter;

	@Before
	public void setUp() throws Exception {

		splitTrueTabFilter = new TabFilter( Runtime.getRuntime().availableProcessors(), true );
		splitFalseTabFilter = new TabFilter( Runtime.getRuntime().availableProcessors(), false );
	}

	@Test
	public void testTabFilterWithoutAnnotationSplitTrue() throws Exception {

		String[] filtered = splitTrueTabFilter
				.filter(new String[] { "this is a tab	sample" });
		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 2 but found size == " + filtered.length, filtered.length == 2 );
		assertEachInStringArray( filtered, "this is a tab", "sample" );
	}

	@Test
	public void testTabFilterWithoutAnnotationSplitFalse() throws Exception {

		String[] filtered = splitFalseTabFilter
				.filter(new String[] { "this\tis\tanother\ttab sample" });
		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 3 but found size == " + filtered.length, filtered.length == 3 );
		assertEachInStringArray( filtered, "\t", "\t", "\t" );
	}

	@Test
	public void testTabFilterWithAnnotationSplitTrue() throws Exception {

		TabFilteredSplitTrue filtered = new TabFilteredSplitTrue();
		String[] result = filtered.runFilter();

		assertNotNull( "expecting result != null but found null", result );
		assertTrue( "expecting result.length == 6 but found " + result.length, result.length == 6 );
		assertEachInStringArray( result, "Tab", "filter", "input", "sample", "is", "here." );
	}
	
	@Test
	public void testTabFilterWithAnnotationSplitFalse() throws Exception {
		TabFilteredSplitFalse filtered = new TabFilteredSplitFalse();
		String[] result = filtered.runFilter();

		assertNotNull( "expecting result != null but found null", result );
		assertTrue( "expecting result.length == 5 but found " + result.length, result.length == 5 );
		assertEachInStringArray( result, "\t", "\t", "\t", "\t", "\t" );
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
