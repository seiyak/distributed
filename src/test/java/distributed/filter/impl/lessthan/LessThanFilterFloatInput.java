package distributed.filter.impl.lessthan;

import distributed.input.DistributedInput;

public class LessThanFilterFloatInput implements DistributedInput<Float> {

	public Float[] getInput() {
		return new Float[] { (float) 1.234, (float) 1.235, (float) 1.236 };
	}
}
