package distributed.filter.impl.lessthan;

import distributed.input.DistributedInput;

public class LessThanFilterIntInput implements DistributedInput<Integer> {

	public Integer[] getInput() {
		return new Integer[] { 20, 24, 40, 50, 60 };
	}
}
