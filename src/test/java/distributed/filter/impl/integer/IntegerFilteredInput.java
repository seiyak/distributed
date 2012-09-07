package distributed.filter.impl.integer;

import distributed.input.DistributedInput;

public class IntegerFilteredInput implements DistributedInput<String> {

	public String[] getInput() {
		return new String[] { "Yesterday I had 2 interviews and today will have 3 more interviews." };
	}

}
