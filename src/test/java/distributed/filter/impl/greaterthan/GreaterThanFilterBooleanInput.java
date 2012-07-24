package distributed.filter.impl.greaterthan;

import distributed.input.DistributedInput;

public class GreaterThanFilterBooleanInput implements DistributedInput<Boolean> {

	public Boolean[] getInput() {
		return new Boolean[]{};
	}

}
