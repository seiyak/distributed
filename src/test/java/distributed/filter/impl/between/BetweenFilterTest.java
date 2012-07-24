package distributed.filter.impl.between;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.BetweenFilter;

public class BetweenFilterTest {

	private BetweenFilter<Integer> betweenFilterInt;
	private BetweenFilter<Double> betweenFilterDouble;
	private BetweenFilter<Float> betweenFilterFloat;
	private BetweenFilter<Boolean> betweenFilterBoolean;

	@Before
	public void setUp() throws Exception {

		betweenFilterInt = new BetweenFilter<Integer>( 4, 10, 20 );
		betweenFilterDouble = new BetweenFilter<Double>( Runtime.getRuntime().availableProcessors(), 1.1, 5.2 );
		betweenFilterFloat = new BetweenFilter<Float>( 2, (float) 0.01, (float) 0.01 );
		betweenFilterBoolean = new BetweenFilter<Boolean>( 1, true, false );
	}

	@Test
	public void testBetweenFilterWithoutAnnotationInteger() throws Exception {

		Integer[] ints = { 5, 10, 15, 20, 30, 50 };
		Integer[] expected = { 10, 15, 20 };

		assertEachElement( expected, betweenFilterInt.filter( ints ).toArray( new Integer[expected.length] ) );
	}

	@Test
	public void testBetweenFilterWithAnnotationInteger() throws Exception {

		BetweenFilteredInt filtered = new BetweenFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray( new Integer[resultList.size()] );
		Integer[] expected = {};

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 0 but found " + result.length, result.length == 0 );
		assertEachElement( expected, result );
	}

	@Test
	public void testBetweenFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 1.1, 5.2, 10.4 };
		Double[] expected = { 1.1, 5.2 };

		assertEachElement( expected, betweenFilterDouble.filter( doubles ).toArray( new Double[expected.length] ) );
	}

	@Test
	public void testEqualFilterWithAnnotationDouble() throws Exception {

		BetweenFilteredDouble filtered = new BetweenFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray( new Double[resultList.size()] );
		Double[] expected = { 0.1, 0.2, 0.3, 0.4 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 4 but found " + result.length, result.length == 4 );
		assertEachElement( expected, result );
	}

	@Test
	public void testBetweenFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 0.01, (float) 0.02 };
		Float[] expected = { (float) 0.01 };

		assertEachElement( expected, betweenFilterFloat.filter( floats ).toArray( new Float[expected.length] ) );
	}

	@Test
	public void testBetweenFilterWithAnnotationFloat() throws Exception {

		BetweenFilteredFloat filtered = new BetweenFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray( new Float[resultList.size()] );
		Float[] expected = { (float) 0.01 };

		assertNotNull( "expecting not null nut found null", result );
		assertTrue( "expecting result.length == 1 but found " + result.length, result.length == 1 );
		assertEachElement( expected, result );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBetweenFilterWithoutAnnotationBoolean() throws Exception {

		Boolean[] booleans = { true, false };
		Boolean[] expected = {};

		betweenFilterBoolean.filter( booleans );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBetweenFilterWithAnnotationBoolean() throws Exception {

		BetweenFilteredBoolean filtered = new BetweenFilteredBoolean();
		List<Boolean> resultList = filtered.runFilter();
	}

	private <T> void assertEachElement(T[] expected, T[] result) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Reflector<T> reflector = new Reflector<T>();

		assertTrue( "expecting the same length but found expected.length == " + expected.length + ", result.length == "
				+ result.length, expected.length == result.length );

		for ( int i = 0; i < expected.length; i++ ) {
			assertTrue( "expecting the same value buf found expected[" + i + "] == " + expected[i] + ", result[" + i
					+ "] == " + result[i], reflector.compareTo( expected[i], result[i] ) == 0 );
		}
	}
}
