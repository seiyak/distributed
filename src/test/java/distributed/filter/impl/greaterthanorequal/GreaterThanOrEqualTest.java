package distributed.filter.impl.greaterthanorequal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.GreaterThanOrEqualFilter;

public class GreaterThanOrEqualTest {

	private GreaterThanOrEqualFilter<Integer> greaterThanOrEqualFilterInt;
	private GreaterThanOrEqualFilter<Double> greaterThanOrEqualFilterDouble;
	private GreaterThanOrEqualFilter<Float> greaterThanOrEqualFilterFloat;
	private GreaterThanOrEqualFilter<Boolean> greaterThanOrEqualFilterBoolean;

	@Before
	public void setUp() throws Exception {

		greaterThanOrEqualFilterInt = new GreaterThanOrEqualFilter<Integer>( 3, 5 );
		greaterThanOrEqualFilterDouble = new GreaterThanOrEqualFilter<Double>( 1, 0.001 );
		greaterThanOrEqualFilterFloat = new GreaterThanOrEqualFilter<Float>( 4, (float) 1 );
		greaterThanOrEqualFilterBoolean = new GreaterThanOrEqualFilter<Boolean>( 6, false );

	}

	@Test
	public void testGreaterThanOrEqualFilterWithoutAnnotationInteger() throws Exception {

		Integer[] ints = { 1, 2, 3, 4, 5, 6 };
		Integer[] expected = { 5, 6 };

		assertEachElement( expected, greaterThanOrEqualFilterInt.filter( ints ).toArray( new Integer[expected.length] ) );
	}

	@Test
	public void testGreaterThanOrEqualFilterWithAnnotationInteger() throws Exception {

		GreaterThanOrEqualFilteredInt filtered = new GreaterThanOrEqualFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray( new Integer[resultList.size()] );
		Integer[] expected = { 60 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 1 but found " + result.length, result.length == 1 );
		assertEachElement( expected, result );
	}

	@Test
	public void testGreaterThanOrEqualFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 0.01, 0.09, 0.0, 0.1 };
		Double[] expected = { 0.01, 0.09, 0.1 };

		assertEachElement( expected,
				greaterThanOrEqualFilterDouble.filter( doubles ).toArray( new Double[expected.length] ) );
	}

	@Test
	public void testGreaterThanOrEqualFilterWithAnnotationDouble() throws Exception {

		GreaterThanOrEqualFilteredDouble filtered = new GreaterThanOrEqualFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray( new Double[resultList.size()] );
		Double[] expected = {};

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 0 but found " + result.length, result.length == 0 );
		assertEachElement( expected, result );
	}

	@Test
	public void testGreaterThanOrEqualFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 0.0, (float) 1, (float) 2 };
		Float[] expected = { (float) 1, (float) 2 };

		assertEachElement( expected, greaterThanOrEqualFilterFloat.filter( floats )
				.toArray( new Float[expected.length] ) );
	}

	@Test
	public void testGreaterThanOrEqualFilterWithAnnotationFloat() throws Exception {

		GreaterThanOrEqualFilteredFloat filtered = new GreaterThanOrEqualFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray( new Float[resultList.size()] );
		Float[] expected = { (float) 1.234, (float) 1.235, (float) 1.236 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 3 but found " + result.length, result.length == 3 );
		assertEachElement( expected, result );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGreaterThanOrEqualFilterWithoutAnnotationBoolean() throws Exception {

		greaterThanOrEqualFilterBoolean.filter( new Boolean[] { false } );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGreaterThanOrEqualFilterWithAnnotationBoolean() throws Exception {

		GreaterThanOrEqualFilteredBoolean filtered = new GreaterThanOrEqualFilteredBoolean();
		filtered.runFilter();
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
