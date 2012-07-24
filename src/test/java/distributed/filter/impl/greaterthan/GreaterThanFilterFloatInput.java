package distributed.filter.impl.greaterthan;

import distributed.input.DistributedInput;

public class GreaterThanFilterFloatInput implements DistributedInput<Float> {

	public Float[] getInput() {
		return new Float[] { (float) 0.345, (float) 0.23, (float) 0.2 };
	}
}
