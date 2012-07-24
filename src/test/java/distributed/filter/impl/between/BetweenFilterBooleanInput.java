package distributed.filter.impl.between;

import distributed.input.DistributedInput;

public class BetweenFilterBooleanInput implements DistributedInput<Boolean> {

	public Boolean[] getInput() {
		return new Boolean[] { true, true, false };
	}

}
