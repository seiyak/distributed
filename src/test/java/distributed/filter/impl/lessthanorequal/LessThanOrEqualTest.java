package distributed.filter.impl.lessthanorequal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.LessThanOrEqualFilter;

public class LessThanOrEqualTest {

	private LessThanOrEqualFilter<Integer> lessThanOrEqualFilterInt;
	private LessThanOrEqualFilter<Double> lessThanOrEqualFilterDouble;
	private LessThanOrEqualFilter<Float> lessThanOrEqualFilterFloat;
	private LessThanOrEqualFilter<Boolean> lessThanOrEqualFilterBoolean;

	@Before
	public void setUp() throws Exception {

		lessThanOrEqualFilterInt = new LessThanOrEqualFilter<Integer>( 1, 2 );
		lessThanOrEqualFilterDouble = new LessThanOrEqualFilter<Double>( 3, 1.11 );
		lessThanOrEqualFilterFloat = new LessThanOrEqualFilter<Float>( 1, (float) 0.01 );
		lessThanOrEqualFilterBoolean = new LessThanOrEqualFilter<Boolean>( 4, false );
	}

	@Test
	public void testLessThanOrEqualFilterWithoutAnnotationInteger() throws Exception {

		Integer[] ints = { 0, 1 };
		Integer[] expected = { 0, 1 };

		assertEachElement( expected, lessThanOrEqualFilterInt.filter( ints ).toArray( new Integer[expected.length] ) );
	}

	@Test
	public void testLessThanOrEqualFilterWithAnnotationInteger() throws Exception {

		LessThanOrEqualFilteredInt filtered = new LessThanOrEqualFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray( new Integer[resultList.size()] );
		Integer[] expected = { 20 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 1 but found " + result.length, result.length == 1 );
		assertEachElement( expected, result );

	}

	@Test
	public void testLessThanOrEqualFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 0.001, (double) 1, 1.11, 1.111 };
		Double[] expected = { 0.001, (double) 1, 1.11 };

		assertEachElement( expected, lessThanOrEqualFilterDouble.filter( doubles )
				.toArray( new Double[expected.length] ) );
	}

	@Test
	public void testLessThanOrEqualFilterWithAnnotationDouble() throws Exception {

		LessThanOrEqualFilteredDouble filtered = new LessThanOrEqualFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray( new Double[resultList.size()] );
		Double[] expected = {};

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 0 but found " + result.length, result.length == 0 );
		assertEachElement( expected, result );
	}

	@Test
	public void testLessThanOrEqualFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 0.011, (float) 0.012, (float) 0.0001, (float) 0.01 };
		Float[] expected = { (float) 0.0001, (float) 0.01 };

		assertEachElement( expected, lessThanOrEqualFilterFloat.filter( floats ).toArray( new Float[expected.length] ) );
	}

	@Test
	public void testLessThanOrEqualFilterWithAnnotationFloat() throws Exception {

		LessThanOrEqualFilteredFloat filtered = new LessThanOrEqualFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray( new Float[resultList.size()] );
		Float[] expected = { (float) 1.234, (float) 1.235, (float) 1.236 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 3 but found " + result.length, result.length == 3 );
		assertEachElement( expected, result );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLessThanOrEqualFilterWithoutAnnotationBoolean() throws Exception {

		Boolean[] booleans = { false, false, false };

		lessThanOrEqualFilterBoolean.filter( booleans );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLessThanOrEqualFilterWithAnnotationBoolean() throws Exception {

		LessThanOrEqualFilteredBoolean filtered = new LessThanOrEqualFilteredBoolean();
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
