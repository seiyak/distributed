package distributed.filter.impl.lessthan;

import distributed.input.DistributedInput;

public class LessThanFilterDoubleInput implements DistributedInput<Double> {

	public Double[] getInput() {
		return new Double[] { 1.23 };
	}
}
