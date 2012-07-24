package distributed.filter.impl.greaterthan;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.GreaterThanFilter;

public class GreaterThanFilterTest {

	private GreaterThanFilter<Integer> greaterThanFilterInt;
	private GreaterThanFilter<Double> greaterThanFilterDouble;
	private GreaterThanFilter<Float> greaterThanFilterFloat;
	private GreaterThanFilter<Boolean> greaterThanFilterBoolean;

	@Before
	public void setUp() throws Exception {

		greaterThanFilterInt = new GreaterThanFilter<Integer>(5, 2);
		greaterThanFilterDouble = new GreaterThanFilter<Double>(4, 30.2);
		greaterThanFilterFloat = new GreaterThanFilter<Float>(2, (float) 3.134);
		greaterThanFilterBoolean = new GreaterThanFilter<Boolean>(5, false);
	}

	@Test
	public void testGreaterThanFilterWithoutAnnotationInteger()
			throws Exception {

		Integer[] ints = { 1, 2, 4, 6, 8 };
		Integer[] expected = { 4, 6, 8 };

		assertEachElement(
				expected,
				greaterThanFilterInt.filter(ints).toArray(
						new Integer[expected.length]));
	}

	@Test
	public void testGraeterThanFilterWithAnnotationInteger() throws Exception {

		GreaterThanFilteredInt filtered = new GreaterThanFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray(new Integer[resultList.size()]);
		Integer[] expected = { 10, 20, 30, 40, 50 };

		assertNotNull("expecting not null but found null", result);
		assertTrue("expecting reuslt.length == 5 but found " + result.length,
				result.length == 5);
		assertEachElement(expected, result);
	}

	@Test
	public void testGreaterThanFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 30.1, 30.2, 30.3, 30.3 };
		Double[] expected = { 30.3, 30.3 };

		assertEachElement(expected, greaterThanFilterDouble.filter(doubles)
				.toArray(new Double[expected.length]));
	}

	@Test
	public void testGreaterThanFilterWithAnnotationDouble() throws Exception {

		GreaterThanFilteredDouble filtered = new GreaterThanFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray(new Double[resultList.size()]);
		Double[] expected = { 4.3, 4.3 };

		assertNotNull("expecting not null but found null", result);
		assertTrue("expecting reuslt.length == 2 but found " + result.length,
				result.length == 2);
		assertEachElement(expected, result);
	}

	@Test
	public void testGreaterThanFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 0.234, (float) 0.2345, (float) 0.6789 };
		Float[] expected = {};

		assertEachElement(expected, greaterThanFilterFloat.filter(floats)
				.toArray(new Float[expected.length]));
	}
	
	@Test
	public void testGreaterThanFilterWithAnnotationFloat() throws Exception {
		
		GreaterThanFilteredFloat filtered = new GreaterThanFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray(new Float[resultList.size()]);
		Float[] expected = { (float) 0.345, (float) 0.23, (float) 0.2 };

		assertNotNull("expecting not null but found null", result);
		assertTrue("expecting reuslt.length == 3 but found " + result.length,
				result.length == 3);
		assertEachElement(expected, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGreaterThanFilterWithoutAnnotationBoolean()
			throws Exception {

		Boolean[] booleans = { false, false, false };

		greaterThanFilterBoolean.filter(booleans);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGreaterThanFilterWithAnnotationBoolean() throws Exception {
		
		Boolean[] booleans = {true};
		
		greaterThanFilterBoolean.filter(booleans);
	}

	private <T> void assertEachElement(T[] expected, T[] result)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Reflector<T> reflector = new Reflector<T>();

		assertTrue("expecting the same length but found expected.length == "
				+ expected.length + ", result.length == " + result.length,
				expected.length == result.length);

		for (int i = 0; i < expected.length; i++) {
			assertTrue("expecting the same value buf found expected[" + i
					+ "] == " + expected[i] + ", result[" + i + "] == "
					+ result[i],
					reflector.compareTo(expected[i], result[i]) == 0);
		}
	}
}
