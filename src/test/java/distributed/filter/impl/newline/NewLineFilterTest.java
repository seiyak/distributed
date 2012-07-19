package distributed.filter.impl.newline;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import distributed.filter.impl.NewLineFilter;

public class NewLineFilterTest {

	private NewLineFilter splitTrueNewLineFilter;
	private NewLineFilter splitFalseNewLineFilter;

	@Before
	public void setUp() throws Exception {
		splitTrueNewLineFilter = new NewLineFilter( true, Runtime.getRuntime().availableProcessors() );
		splitFalseNewLineFilter = new NewLineFilter( false, Runtime.getRuntime().availableProcessors() );
	}

	@Test
	public void testNewLineFilterWithoutAnnotationSplitTrue() throws Exception {

		List<String> result = splitTrueNewLineFilter
				.filter(new String[] { "first\nsecond\nthird\nfourth\nfifth\nsixth\nseventh\nandeighth" });
		String[] filtered = result.toArray(new String[result.size()]);

		assertNotNull( "expecting not nul; but found null", filtered );
		assertTrue( "expecting size == 8 but found size == " + filtered.length, filtered.length == 8 );
		assertEachInStringArray( filtered, "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
				"andeighth" );
	}

	@Test
	public void testNewLineFilterWithoutAnnotationSplitFalse() throws Exception {

		List<String> result = splitFalseNewLineFilter
				.filter(new String[] { "this\nis\nanother\ninput" });
		String[] filtered = result.toArray(new String[result.size()]);

		assertNotNull( "expecting not null but found null", filtered );
		assertTrue( "expecting size == 3 but found size == " + filtered.length, filtered.length == 3 );
		assertEachInStringArray( filtered, "\n", "\n", "\n" );
	}

	@Test
	public void testNewLineFilterWithAnnotationSplitTrue() throws Exception {

		NewLineFilteredSplitTrue filtered = new NewLineFilteredSplitTrue();
		List<String> resultList = filtered.runFilter();
		String[] result = resultList.toArray(new String[resultList.size()]);

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 3 but found " + result.length, result.length == 3 );
		assertEachInStringArray( result, "this", "isfor", "new line filter." );
	}

	@Test
	public void testNewLineFilterWithAnnotationSplitFalse() throws Exception {

		NewLineFilteredSplitFalse filtered = new NewLineFilteredSplitFalse();
		List<String> resultList = filtered.runFilter();
		String[] result = resultList.toArray(new String[resultList.size()]);

		assertNotNull( "expecting result != null but found null", result );
		assertTrue( "expecing result.length == 2 but found " + result.length, result.length == 2 );
		assertEachInStringArray( result, "\n", "\n" );
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
