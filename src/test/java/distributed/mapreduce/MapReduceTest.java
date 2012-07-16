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
	private AbstractMapReduce<Map<String, Integer>> mapreduce;
	private distributed.map.Map<String> map;
	private Reduce<Map<String, Integer>> reduce;
	private AbstractMapReduce<Map<String,Integer>> mapreduceMap;
	private AbstractMapReduce<Map<String,Integer>> mapreduceReduce;
	private static int SHUFFLED_SIZE_FOR_TESTINPUT_1 = 9;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mapreduce = new MapReduce();
		map = new distributed.map.Map<String>( null, 3 );
		reduce = new Reduce<Map<String, Integer>>( null, 5 );
		mapreduceMap = new MapReduceMap();
		mapreduceReduce = new MapReduceReduce();
	}

	@Test
	public void testRunMapPhaseLocally() throws Exception {

		Map<String, ArrayList<Object>> shuffled = mapreduce.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
		// printShuffledIntermediateResult( shuffled );
	}

	@Test
	public void testRunIndependentMapPhaseLocally() throws Exception {

		map.setMapper( new MapperImpl() );
		map.setMapInput( new MapInputImpl() );
		Map<String, ArrayList<Object>> shuffled = map.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
	}
	
	@Test
	public void testRunOnlyAnnotatedMapPhaseLocally() throws Exception {
		Map<String, ArrayList<Object>> shuffled = mapreduceMap.runMapPahse();
		assertNotNull( "expecting shuffled != null but found null", shuffled );
		assertTrue( "expecting size == " + SHUFFLED_SIZE_FOR_TESTINPUT_1 + " but found " + shuffled.size(),
				shuffled.size() == SHUFFLED_SIZE_FOR_TESTINPUT_1 );
		assertShuffledIntermediateResult( shuffled );
	}

	@Test(expected = IllegalStateException.class)
	public void testRunOnlyAnnotatedMapPhaseLocallyThrowsException() throws Exception {
		Map<String, ArrayList<Object>> shuffled = mapreduceMap.runMapPahse();
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduceMap.runReducePhase( shuffled, reduced );
	}
	
	private void assertShuffledIntermediateResult(Map<String, ArrayList<Object>> shuffled) {
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

	private void assertShuffledList(String key, int expectedSize, ArrayList<Object> shuffledList) {
		assertTrue( "expecting '" + key + "'has size == " + expectedSize + " but found " + shuffledList.size(),
				shuffledList.size() == expectedSize );
		for ( int i = 0; i < shuffledList.size(); i++ ) {
			assertTrue( "expecting 1 but found " + shuffledList.get( i ), (Integer) shuffledList.get( i ) == 1 );
		}
	}

	private void printIntermediateResult(IntermediateResult[] intermediateResult) {
		for ( int i = 0; i < intermediateResult.length; i++ ) {
			log.info( "index: " + i + " key: " + intermediateResult[i].getKey() + " value: "
					+ intermediateResult[i].getValue() );
		}
	}

	private void printShuffledIntermediateResult(Map<String, ArrayList<Object>> shuffled) {
		for ( Iterator<Entry<String, ArrayList<Object>>> itr = shuffled.entrySet().iterator(); itr.hasNext(); ) {
			Entry<String, ArrayList<Object>> entry = itr.next();
			log.info( "shuffled entry key: " + entry.getKey() + " values: " + entry.getValue() );
		}
	}

	@Test
	public void testRunReducePhaseLocally() throws Exception {
		Map<String, ArrayList<Object>> shuffled = mapreduce.runMapPahse();
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
		
		Map<String,ArrayList<Object>> reduceInput = new HashMap<String,ArrayList<Object>>();
		ArrayList<Object> array1 = new ArrayList<Object>();
		for(int i = 0; i < 200;i++){
			array1.add( 1 );
		}
		reduceInput.put( "dummy", array1 );
		ArrayList<Object> array2 = new ArrayList<Object>();
		for(int i = 0; i < 20;i++){
			array2.add( 1 );
		}
		reduceInput.put( "dummy2", array2 );
		
		reduce.setReducer( new ReducerImpl() );
		
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduce.runReducePhase( reduceInput, reduced );
		
		assertNotNull( "expecting reduce is not null but found null", reduced );
		assertTrue("expecting 'dummy' has 200 but found " + reduced.get( "dummy" ),reduced.get( "dummy" ) == 200);
		assertTrue("expecting 'dummy2' has 20 but found " + reduced.get( "dummy2" ),reduced.get( "dummy2" ) == 20);
	}
	
	@Test
	public void testRunOnlyAnnotatedReducePhaseLocally() throws Exception{
		Map<String,ArrayList<Object>> reduceInput = new HashMap<String,ArrayList<Object>>();
		ArrayList<Object> array1 = new ArrayList<Object>();
		for(int i = 0; i < 200;i++){
			array1.add( 1 );
		}
		reduceInput.put( "dummy", array1 );
		ArrayList<Object> array2 = new ArrayList<Object>();
		for(int i = 0; i < 20;i++){
			array2.add( 1 );
		}
		reduceInput.put( "dummy2", array2 );
		
		Map<String, Integer> reduced = new HashMap<String, Integer>();
		mapreduceReduce.runReducePhase( reduceInput, reduced );
		
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

	private void printReducedResult(Map<String, Integer> reduced) {

		for ( Entry<String, Integer> entry : reduced.entrySet() ) {
			log.info( "key: " + entry.getKey() + " value: " + entry.getValue() );
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
