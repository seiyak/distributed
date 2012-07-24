package distributed.filter.impl.lessthan;

import distributed.input.DistributedInput;

public class LessThanFilterBooleanInput implements DistributedInput<Boolean> {

	public Boolean[] getInput() {
		return new Boolean[] { true, false, false, true };
	}

}
