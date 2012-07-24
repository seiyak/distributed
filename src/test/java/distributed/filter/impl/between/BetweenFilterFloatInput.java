package distributed.filter.impl.between;

import distributed.input.DistributedInput;

public class BetweenFilterFloatInput implements DistributedInput<Float> {

	public Float[] getInput() {
		return new Float[] { (float) 0.01, (float) 0.02, (float) 0.03 };
	}
}
