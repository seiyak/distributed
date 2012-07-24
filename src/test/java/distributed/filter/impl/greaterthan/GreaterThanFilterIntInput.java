package distributed.filter.impl.greaterthan;

import distributed.input.DistributedInput;

public class GreaterThanFilterIntInput implements DistributedInput<Integer> {

	public Integer[] getInput() {
		return new Integer[] { 10, 20, 30, 40, 50 };
	}

}
