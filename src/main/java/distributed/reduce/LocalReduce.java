package distributed.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LocalReduce<R> implements Callable<R> {

	private final int start;
	private final int end;
	private final Reducer<R> reducer;
	private final Map<String, ArrayList<Object>> reduceInput;

	public LocalReduce(int start, int end, Reducer<R> reducer, Map<String, ArrayList<Object>> reduceInput) {

		this.start = start;
		this.end = end;
		this.reducer = reducer;
		this.reduceInput = reduceInput;
	}

	public R call() throws Exception {
		
		String[] keys = reduceInput.keySet().toArray(new String[reduceInput.size()]);
		List[] values = reduceInput.values().toArray(new ArrayList[reduceInput.size()]);
		return reducer.reduce(Arrays.copyOfRange( keys, start, end ), Arrays.copyOfRange( values, start, end ) );
	}
}
