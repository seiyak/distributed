package distributed.filter.impl.equal;

import distributed.input.DistributedInput;

public class EqualFilterBooleanInput implements DistributedInput<Boolean> {

	public Boolean[] getInput() {
		return new Boolean[] { false, false, false, false, false, true, false };
	}
}
