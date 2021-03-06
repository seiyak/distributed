package distributed.map;

import java.util.Arrays;
import java.util.concurrent.Callable;

import distributed.input.DistributedInput;

public class LocalMap<I> implements Callable<IntermediateResult[]> {

	private final int start;
	private final int end;
	private final Mapper mapper;
	private final DistributedInput<I> mapInput;

	public LocalMap(int start, int end, final Mapper mapper, final DistributedInput<I> mapInput) {
		this.start = start;
		this.end = end;
		this.mapper = mapper;
		this.mapInput = mapInput;
	}

	public IntermediateResult[] call() throws Exception {

		return mapper.map( Arrays.copyOfRange( mapInput.getInput(), start, end ) );
	}
}
