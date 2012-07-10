package distributed.filter.impl;

import distributed.input.DistributedInput;

public class FilteredInput implements DistributedInput {

	public Object[] getInput() {
		return new String[] { "Annotated filter example here." };
	}

}
