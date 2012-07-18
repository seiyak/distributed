package distributed.filter.impl.newline;

import distributed.input.DistributedInput;

public class NewLineFilterInput implements DistributedInput<String> {

	public String[] getInput() {
		return new String[] { "this\nisfor\nnew line filter." };
	}

}
