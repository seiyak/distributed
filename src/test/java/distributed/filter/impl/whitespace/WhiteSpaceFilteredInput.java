package distributed.filter.impl.whitespace;

import distributed.input.DistributedInput;

public class WhiteSpaceFilteredInput implements DistributedInput {

	public Object[] getInput() {
		return new String[] { "Annotated filter example is here." };
	}
}
