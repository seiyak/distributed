package distributed.map;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import distributed.annotation.processor.MapPhaseProcessor;
import distributed.input.DistributedInput;
import distributed.mapreduce.AbstractMapReduce;

public class Map<I> {

	private final int numberOfMap;
	private final Object[] values;
	private Mapper mapper;
	private DistributedInput<I> mapInput;

	public Map(AbstractMapReduce mapreduce, int numberOfMap) {

		if ( mapreduce == null ) {
			values = null;
		}
		else {
			values = new MapPhaseProcessor( mapreduce ).process();
		}

		this.numberOfMap = numberOfMap;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public void setMapInput(DistributedInput<I> mapInput) {
		this.mapInput = mapInput;
	}

	@SuppressWarnings("unchecked")
	public java.util.Map<String, ArrayList<Object>> runMapPahse() throws Exception {

		if ( values != null ) {
			mapper = (Mapper) ( (Class) values[0] ).newInstance();
			mapInput = (DistributedInput<I>) ( (Class) values[1] ).newInstance();
		}

		checkNullBeforeMapPhase( mapper, mapInput );

		return shuffle( doMapPhase( mapper, mapInput ) );
	}

	private java.util.Map<String, ArrayList<Object>> shuffle(IntermediateResult[] intermediateResult) {

		ConcurrentMap<String, ArrayList<Object>> map = new ConcurrentHashMap<String, ArrayList<Object>>();
		// TODO make use of divide and conquer
		for ( IntermediateResult iResult : intermediateResult ) {

			if ( map.containsKey( iResult.getKey() ) ) {
				map.get( iResult.getKey() ).add( iResult.getValue() );
			}
			else if ( !map.containsKey( iResult.getKey() ) ) {
				map.put( iResult.getKey(), new ArrayList<Object>() );
				map.get( iResult.getKey() ).add( iResult.getValue() );
			}
		}
		return map;
	}

	private void checkNullBeforeMapPhase(Mapper mapper, DistributedInput<I> mapInput) {

		if ( mapper == null || mapInput == null ) {
			if ( mapper == null && mapInput != null ) {
				throw new IllegalStateException( "mapper must not be null but found null in map phase" );
			}
			else if ( mapper != null && mapInput == null ) {
				throw new IllegalStateException( "mapperInput must not be null but found null in map phase" );
			}
			else if ( mapper == null && mapInput == null ) {
				throw new IllegalStateException(
						"mapper and mapInput must not be null but found they are null in map phase" );
			}
		}
		else if ( mapInput != null && mapInput.getInput() == null ) {
			throw new IllegalStateException( "mapInput is not null but mapInput.getInput() returns null in map phase" );
		}
	}

	private IntermediateResult[] doMapPhase(final Mapper mapper, final DistributedInput<I> mapInput) throws Exception {

		return doMapPhaseLocally( mapper, mapInput );
		// TODO support distributed map phase
	}

	private IntermediateResult[] doMapPhaseLocally(final Mapper mapper, final DistributedInput<I> mapInput) throws Exception {

		IntermediateResult[] result = new IntermediateResult[mapInput.getInput().length];
		final int leftOver = mapInput.getInput().length % numberOfMap;
		final int numberOfTask = mapInput.getInput().length / numberOfMap;
		int i = 0;

		//TODO should it be numberOfMap or number of available CPUs ?
		for ( i = 0; i < numberOfMap; i++ ) {
			int start = i * numberOfTask;
			int end = start + numberOfTask;
			System.arraycopy( new LocalMap<I>( start, end, mapper, mapInput ).call(), 0, result, start, end - start );

			if ( i == ( numberOfMap - 1 ) && leftOver != 0 ) {
				if ( leftOver <= numberOfMap ) {
					int newStart = end;
					int newEnd = newStart + leftOver;
					System.arraycopy( new LocalMap<I>( newStart, newEnd, mapper, mapInput ).call(), 0, result, newStart,
							newEnd - newStart );
				}
			}
		}

		return result;
	}

}
