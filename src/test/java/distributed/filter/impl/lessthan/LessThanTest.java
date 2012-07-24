package distributed.filter.impl.lessthan;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.LessThanFilter;

public class LessThanTest {

	private LessThanFilter<Integer> lessThanFilterInt;
	private LessThanFilter<Double> lessThanFilterDouble;
	private LessThanFilter<Float> lessThanFilterFloat;
	private LessThanFilter<Boolean> lessThanFilterBoolean;

	@Before
	public void setUp() throws Exception {

		lessThanFilterInt = new LessThanFilter<Integer>( 3, 100 );
		lessThanFilterDouble = new LessThanFilter<Double>( 2, 90.23 );
		lessThanFilterFloat = new LessThanFilter<Float>( 1, (float) 20.32 );
		lessThanFilterBoolean = new LessThanFilter<Boolean>( 8, true );
	}

	@Test
	public void testLessThanFilterWithoutAnnotationInteger() throws Exception {

		Integer[] ints = { 90, 95, 100 };
		Integer[] expected = { 90, 95 };

		assertEachElement( expected, lessThanFilterInt.filter( ints ).toArray( new Integer[expected.length] ) );
	}

	@Test
	public void testLessThanFilterWithAnnotationInteger() throws Exception {

		LessThanFilteredInt filtered = new LessThanFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray( new Integer[resultList.size()] );
		Integer[] expected = { 20, 24, 40, 50 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.legnth == 4 but found " + result.length, result.length == 4 );
		assertEachElement( expected, result );
	}

	@Test
	public void testLessThanFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 90.12, 90.2, 90.22, 90.23, 90.24 };
		Double[] expected = { 90.12, 90.2, 90.22 };

		assertEachElement( expected, lessThanFilterDouble.filter( doubles ).toArray( new Double[expected.length] ) );
	}

	@Test
	public void testLessThanFilterWithAnnotationDouble() throws Exception {

		LessThanFilteredDouble filtered = new LessThanFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray( new Double[resultList.size()] );
		Double[] expected = {};

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 0 but found" + result.length, result.length == 0 );
		assertEachElement( expected, result );
	}

	@Test
	public void testLessThanFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 20.32, (float) 20.33, (float) 21 };
		Float[] expected = {};

		assertEachElement( expected, lessThanFilterFloat.filter( floats ).toArray( new Float[expected.length] ) );
	}

	@Test
	public void testLessThanFilterWithAnnotationFloat() throws Exception {

		LessThanFilteredFloat filtered = new LessThanFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray( new Float[resultList.size()] );
		Float[] expected = { (float) 1.234 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 1 but found " + result.length, result.length == 1 );
		assertEachElement( expected, result );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLessThanFilterWithoutAnnotationBoolean() throws Exception {

		Boolean[] booleans = { false, false, false };
		Boolean[] expected = {};

		lessThanFilterBoolean.filter( booleans );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLessThanFilterWithAnnotationBoolean() throws Exception {

		LessThanFilteredBoolean filtered = new LessThanFilteredBoolean();
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
