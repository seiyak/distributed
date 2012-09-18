package distributed.filter.impl;

import distributed.input.DistributedInput;

public class DoubleFilteredInput implements DistributedInput<String> {

	public String[] getInput() {
		return new String[] { "I got 99.3 out of 100.00 on yesterday's exam. Feeling good" };
	}

}
