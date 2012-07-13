package distributed.filter.impl.newline;

import distributed.input.DistributedInput;

public class NewLineFilterInput implements DistributedInput {

	public Object[] getInput() {
		return new String[] { "this\nisfor\nnew line filter." };
	}

}
