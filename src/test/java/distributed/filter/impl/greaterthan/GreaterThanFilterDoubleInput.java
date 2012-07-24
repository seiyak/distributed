package distributed.filter.impl.greaterthan;

import distributed.input.DistributedInput;

public class GreaterThanFilterDoubleInput implements DistributedInput<Double> {

	public Double[] getInput() {
		return new Double[] { 2.1, 4.3, 2.1, 4.3 };
	}
}
