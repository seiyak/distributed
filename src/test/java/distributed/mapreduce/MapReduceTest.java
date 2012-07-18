package distributed.mapreduce;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import distributed.map.IntermediateResult;
import distributed.mapreduce.AbstractMapReduce;
import distributed.reduce.Reduce;

public class MapReduceTest {

	private static Logger log = Logger.getLogger( MapReduceTest.class );
	private AbstractMapReduce<String, String, Integer> mapreduce;
	private AbstractMapReduce<Integer, String, Integer> mapreduceInteger;
	private distributed.map.Map<String, Integer> map;
	private Reduce<Integer, Map<String, Integer>> reduce;
	private AbstractMapReduce<String, String, Integer> mapreduceMap;
	private AbstractMapReduce<String, String, Integer> mapreduceReduce;
	private AbstractMapReduce<Integer, String,Integer> mapreduceReduceInteger;
	private static int SHUFFLED_SIZE_FOR_TESTINPUT_1 = 9;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mapreduce = new MapReduce();
		map = new distributed.map.Map<String, Integer>( null, 3 );
		reduce = new Reduce<Integer, Map<String, Integer>>( null, 5 );
		mapreduceMap = new MapReduceMap();
		mapreduceReduce = new MapReduceReduce();
		mapreduceInteger = new MapReduceInt();
		mapreduceReduceInteger = new MapReduceReduceInt();
	}

	@Test
	public void testRunMapPhaseLocally() throws Exception {

		Map<String, ArrayList<Integer>> shuffled = mapreduce.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
	}

	@Test
	public void testRunIndependentMapPhaseLocally() throws Exception {

		map.setMapper( new MapperImpl() );
		map.setMapInput( new MapInputImpl() );
		Map<String, ArrayList<Integer>> shuffled = map.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
	}

	@Test
	public void testRunOnlyAnnotatedMapPhaseLocally() throws Exception {
		Map<String, ArrayList<Integer>> shuffled = mapreduceMap.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
	}

	@Test(expected = IllegalStateException.class)
	public void testRunOnlyAnnotatedMapPhaseLocallyThrowsException() throws Exception {
		Map<String, ArrayList<Integer>> shuffled = mapreduceMap.runMapPahse();
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduceMap.runReducePhase( shuffled, reduced );
	}

	private void assertShuffledIntermediateResult(Map<String, ArrayList<Integer>> shuffled) {
		assertTrue( "expecting 'First' has size == 1 but found " + shuffled.get( "First" ).size(),
				shuffled.get( "First" ).size() == 1 );
		assertTrue( "expecting 'line.' has size == 4 but found " + shuffled.get( "line." ).size(),
				shuffled.get( "line." ).size() == 4 );

		assertShuffledList( "First", 1, shuffled.get( "First" ) );
		assertShuffledList( "line.", 4, shuffled.get( "line." ) );
		assertShuffledList( "This", 2, shuffled.get( "This" ) );
		assertShuffledList( "is", 2, shuffled.get( "is" ) );
		assertShuffledList( "the", 2, shuffled.get( "the" ) );
		assertShuffledList( "first", 1, shuffled.get( "first" ) );
		assertShuffledList( "test", 2, shuffled.get( "test" ) );
		assertShuffledList( "Second", 1, shuffled.get( "Second" ) );
		assertShuffledList( "second", 1, shuffled.get( "second" ) );
	}

	private void assertShuffledList(String key, int expectedSize, ArrayList<Integer> shuffledList) {
		assertTrue( "expecting '" + key + "'has size == " + expectedSize + " but found " + shuffledList.size(),
				shuffledList.size() == expectedSize );

		for ( int i = 0; i < shuffledList.size(); i++ ) {
			assertTrue( "expecting 1 but found " + shuffledList.get( i ), shuffledList.get( i ) == 1 );
		}
	}

	@Test
	public void testRunReducePhaseLocally() throws Exception {
		Map<String, ArrayList<Integer>> shuffled = mapreduce.runMapPahse();
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduce.runReducePhase( shuffled, reduced );

		assertNotNull( "expecting reduce is not null but found null", reduced );
		assertReducedResult( "First", 1, reduced );
		assertReducedResult( "line.", 4, reduced );
		assertReducedResult( "This", 2, reduced );
		assertReducedResult( "is", 2, reduced );
		assertReducedResult( "the", 2, reduced );
		assertReducedResult( "first", 1, reduced );
		assertReducedResult( "test", 2, reduced );
		assertReducedResult( "Second", 1, reduced );
		assertReducedResult( "second", 1, reduced );
	}

	@Test
	public void testRunIndependentReducePhaseLocally() throws Exception{
		
		Map<String,ArrayList<Integer>> reduceInput = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		for(int i = 0; i < 200;i++){
			array1.add( 1 );
		}
		reduceInput.put( "dummy", array1 );
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		for(int i = 0; i < 20;i++){
			array2.add( 1 );
		}
		reduceInput.put( "dummy2", array2 );
		
		reduce.setReducer( new ReducerImpl() );
		
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduceInteger.runReducePhase( reduceInput, reduced );
		
		assertNotNull( "expecting reduce is not null but found null", reduced );
		assertTrue("expecting 'dummy' has 200 but found " + reduced.get( "dummy" ),reduced.get( "dummy" ) == 200);
		assertTrue("expecting 'dummy2' has 20 but found " + reduced.get( "dummy2" ),reduced.get( "dummy2" ) == 20);
	}

	@Test
	public void testRunOnlyAnnotatedReducePhaseLocally() throws Exception{
		Map<String,ArrayList<Integer>> reduceInput = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		for(int i = 0; i < 200;i++){
			array1.add( 1 );
		}
		reduceInput.put( "dummy", array1 );
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		for(int i = 0; i < 20;i++){
			array2.add( 1 );
		}
		reduceInput.put( "dummy2", array2 );
		
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduceReduceInteger.runReducePhase( reduceInput, reduced );
		
		assertNotNull( "expecting reduce is not null but found null", reduced );
		assertTrue("expecting 'dummy' has 200 but found " + reduced.get( "dummy" ),reduced.get( "dummy" ) == 200);
		assertTrue("expecting 'dummy2' has 20 but found " + reduced.get( "dummy2" ),reduced.get( "dummy2" ) == 20);
	}

	@Test(expected=IllegalStateException.class)
	public void testRunOnlyAnnotatedReducePhaseLocallyThrowsException() throws Exception {
		mapreduceReduce.runMapPahse();
	}

	@Test
	public void testMapReduce() throws Exception {
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduce.runMapReduce( reduced );

		assertNotNull( "expecting reduce is not null but found null", reduced );
		assertReducedResult( "First", 1, reduced );
		assertReducedResult( "line.", 4, reduced );
		assertReducedResult( "This", 2, reduced );
		assertReducedResult( "is", 2, reduced );
		assertReducedResult( "the", 2, reduced );
		assertReducedResult( "first", 1, reduced );
		assertReducedResult( "test", 2, reduced );
		assertReducedResult( "Second", 1, reduced );
		assertReducedResult( "second", 1, reduced );
	}

	private void assertReducedResult(String key, int expectedValue, Map<String, Integer> reduced) {
		assertTrue( "expecting '" + key + "' has " + expectedValue + " but found " + reduced.get( key ),
				reduced.get( key ) == expectedValue );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
