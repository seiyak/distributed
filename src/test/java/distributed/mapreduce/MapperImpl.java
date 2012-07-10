package distributed.mapreduce;

import distributed.input.DistributedInput;
import distributed.map.IntermediateResult;
import distributed.map.Mapper;

public class MapperImpl implements Mapper {

	public IntermediateResult[] map(Object[] input) {
		String[] in = (String[]) input;

		IntermediateResult[] res = new IntermediateResult[input.length];
		for ( int i = 0; i < input.length; i++ ) {
			res[i] = new IntermediateResult( in[i], 1 );
		}

		return res;
	}

	public void emit(IntermediateResult[] intermediateResults) {
		// TODO Auto-generated method stub

	}

}
