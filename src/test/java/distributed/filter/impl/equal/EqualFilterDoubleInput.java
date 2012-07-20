package distributed.filter.impl.equal;

import distributed.input.DistributedInput;

public class EqualFilterDoubleInput implements DistributedInput<Double> {

	public Double[] getInput() {
		return new Double[] { 0.2, 0.1, 0.0, 0.2, 11.2, 0.56 };
	}

}
