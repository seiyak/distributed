package distributed.filter.impl.equal;

import distributed.input.DistributedInput;

public class EqaulFilterIntInput implements DistributedInput<Integer> {

	public Integer[] getInput() {
		return new Integer[] { 1, 2, 3, 4, 5, 1, 1, 1, 1 };
	}
}
