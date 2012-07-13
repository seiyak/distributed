package distributed.filter.impl.tab;

import distributed.input.DistributedInput;

public class TabFilteredInput implements DistributedInput {

	public Object[] getInput() {
		return new String[]{"Tab\tfilter\tinput\tsample\tis\there."};
	}

}
