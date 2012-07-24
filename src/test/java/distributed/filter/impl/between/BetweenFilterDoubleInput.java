package distributed.filter.impl.between;

import distributed.input.DistributedInput;

public class BetweenFilterDoubleInput implements DistributedInput<Double> {

	public Double[] getInput() {
		return new Double[] { 0.1, 0.2, 0.3, 0.4, 0.5 };
	}

}
