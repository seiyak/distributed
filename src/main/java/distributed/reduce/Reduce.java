package distributed.reduce;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import distributed.annotation.processor.ReducePhaseProcessor;
import distributed.mapreduce.AbstractMapReduce;

public class Reduce<R extends Map> {

	private final Object[] values;
	private final int numberOfReduce;
	private Reducer<R> reducer;

	public Reduce(AbstractMapReduce<R> mapreduce, int numberOfReduce) {
		
		if ( mapreduce == null ) {
			values = null;
		}
		else {
			values = new ReducePhaseProcessor( mapreduce ).process();
		}

		this.numberOfReduce = numberOfReduce;
	}

	public void setReducer(Reducer<R> reducer){
		this.reducer = reducer;
	}
	
	public R runReducePhase(Map<String, ArrayList<Object>> resultFromMapPhase, R result) throws Exception {

		if(values != null){
			reducer = (Reducer<R>) ( (Class) values[0] ).newInstance();
		}

		checkNullBeforeReducePhase( reducer, resultFromMapPhase );
		result = doReducePhase( reducer, resultFromMapPhase, result );
		return result;
	}

	private void checkNullBeforeReducePhase(Reducer<R> reducer, Map<String, ArrayList<Object>> reduceInput) {

		if ( reducer == null || reduceInput == null ) {
			if ( reducer == null && reduceInput != null ) {
				throw new IllegalStateException( "reducer must not be null but found null in reduce phase" );
			}
			else if ( reducer != null && reduceInput == null ) {
				throw new IllegalStateException( "reducerInput must not be null but found null in reduce phase" );
			}
			else if ( reducer == null && reduceInput == null ) {
				throw new IllegalStateException(
						"reducer and reduceInput must not be null but found they are null in reduce phase" );
			}
		}
	}

	private R doReducePhase(final Reducer<R> reducer, final Map<String, ArrayList<Object>> reduceInput, R result)
			throws Exception {

		return doReducePhaseLocally( reducer, reduceInput, result );
		// TODO support distributed reduce phase
	}

	private R doReducePhaseLocally(final Reducer<R> reducer, final Map<String, ArrayList<Object>> reduceInput, R result)
			throws Exception {

		final int numberOfTask = reduceInput.size() / numberOfReduce;
		int leftOver = reduceInput.size() % numberOfReduce;
		int i = 0;

		for ( i = 0; i < numberOfReduce; i++ ) {
			int start = i * numberOfTask;
			int end = start + numberOfTask;
			result.putAll( new LocalReduce<R>( start, end, reducer, reduceInput ).call() );

			if ( i == ( numberOfReduce - 1 ) && leftOver != 0 ) {
				if ( leftOver <= numberOfReduce ) {
					int newStart = end;
					int newEnd = newStart + leftOver;
					result.putAll( new LocalReduce<R>( newStart, newEnd, reducer, reduceInput ).call() );
				}
			}
		}

		return result;
	}

}
