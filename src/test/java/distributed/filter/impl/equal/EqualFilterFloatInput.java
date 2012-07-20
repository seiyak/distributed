package distributed.filter.impl.equal;

import distributed.input.DistributedInput;

public class EqualFilterFloatInput implements DistributedInput<Float> {

	public Float[] getInput() {
		return new Float[] { (float) 0.001, (float) 0.02, (float) 0.001,
				(float) 0.001 };
	}

}
