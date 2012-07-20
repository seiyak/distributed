package distributed.filter.impl.equal;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import distributed.filter.helper.reflect.Reflector;
import distributed.filter.impl.EqualFilter;

public class EqualFilterTest {

	private EqualFilter<Integer> equalFilterInt;
	private EqualFilter<Double> equalFilterDouble;
	private EqualFilter<Float> equalFilterFloat;
	private EqualFilter<Boolean> equalFilterBoolean;

	@Before
	public void setUp() throws Exception {

		equalFilterInt = new EqualFilter<Integer>(20, 20);
		equalFilterDouble = new EqualFilter<Double>(Runtime.getRuntime()
				.availableProcessors(), 2.1);
		equalFilterFloat = new EqualFilter<Float>(1, (float) 10.3);
		equalFilterBoolean = new EqualFilter<Boolean>(10, false);
	}

	@Test
	public void testEqualFilterWithoutAnnotationInteger() throws Exception {

		Integer[] ints = { 20, 20, 20, 20, 10, 3, 5, 2, 5, 1 };
		Integer[] expected = { 20, 20, 20, 20 };

		assertEachElement(
				expected,
				equalFilterInt.filter(ints).toArray(
						new Integer[expected.length]));
	}

	@Test
	public void testEqualFilterWithAnnotationInteger() throws Exception {

		EqualFilteredInt filtered = new EqualFilteredInt();
		List<Integer> resultList = filtered.runFilter();
		Integer[] result = resultList.toArray( new Integer[resultList.size()] );
		Integer[] expected = { 1, 1, 1, 1, 1 };

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 5 but found " + result.length,
				result.length == 5 );
		assertEachElement( expected, result );
	}
	
	@Test
	public void testEqualFilterWithoutAnnotationDouble() throws Exception {

		Double[] doubles = { 1.0, 3.0, 2.1, 53.1 };
		Double[] expected = { 2.1 };

		assertEachElement(
				expected,
				equalFilterDouble.filter(doubles).toArray(
						new Double[expected.length]));
	}

	@Test
	public void testEqualFilterWithAnnotationDouble() throws Exception {
		EqualFilteredDouble filtered = new EqualFilteredDouble();
		List<Double> resultList = filtered.runFilter();
		Double[] result = resultList.toArray( new Double[resultList.size()] );
		Double[] expected = {};

		assertNotNull( "expecting not null but found null", result );
		assertTrue( "expecting result.length == 0 but found " + result.length,
				result.length == 0 );
		assertEachElement( expected, result );
	}

	@Test
	public void testEqualFilterWithoutAnnotationFloat() throws Exception {

		Float[] floats = { (float) 9.2, (float) 2.1, (float) 0.54, (float) 2,
				(float) 10.3, (float) 502, (float) 30, (float) 10.3 };
		Float[] expected = { (float) 10.3, (float) 10.3 };

		assertEachElement(
				expected,
				equalFilterFloat.filter(floats).toArray(
						new Float[expected.length]));
	}

	@Test
	public void testEqualFilterWithAnnotationFloat() throws Exception {

		EqualFilteredFloat filtered = new EqualFilteredFloat();
		List<Float> resultList = filtered.runFilter();
		Float[] result = resultList.toArray(new Float[resultList.size()]);
		Float[] expected = { (float) 0.001, (float) 0.001, (float) 0.001 };

		assertNotNull("expecting not null but found null", result);
		assertTrue("expecting result.length == 3 but found " + result.length,
				result.length == 3);
		assertEachElement(expected, result);
	}

	@Test
	public void testEqualFilterWithoutAnnotationBoolean() throws Exception {

		Boolean[] booleans = { false, true, false, true, false, true, false,
				false };
		Boolean[] expected = { false, false, false, false, false };

		assertEachElement(expected, equalFilterBoolean.filter(booleans)
				.toArray(new Boolean[expected.length]));
	}

	@Test
	public void testEqualFilterWithAnnotationBoolean() throws Exception {

		EqualFilteredBoolean filtered = new EqualFilteredBoolean();
		List<Boolean> resultList = filtered.runFilter();
		Boolean[] result = resultList.toArray(new Boolean[resultList.size()]);
		Boolean[] expected = { false, false, false, false, false, false };

		assertNotNull("expecting not null but found null", result);
		assertTrue("expecting result.length == 6 but found " + result.length,
				result.length == 6);
		assertEachElement(expected, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualFilterWithoutAnnotationInputNull() throws Exception {

		equalFilterInt.filter(null);
	}

	@Test
	public void testEqualFilterWithoutAnnotationResultEmpty() throws Exception {

		Boolean[] booleans = {};
		Boolean[] expected = {};
		assertEachElement(expected, equalFilterBoolean.filter(booleans)
				.toArray(new Boolean[expected.length]));
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
