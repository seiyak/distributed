package distributed.filter.impl.between;

import distributed.input.DistributedInput;

public class BetweenFilterIntInput implements DistributedInput<Integer> {

	public Integer[] getInput() {
		return new Integer[] { 1, 1, 4, 5, 8, 0 };
	}

}
