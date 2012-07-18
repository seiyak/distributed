package distributed.filter.impl.whitespace;

import distributed.input.DistributedInput;

public class WhiteSpaceFilteredInput implements DistributedInput<String> {

	public String[] getInput() {
		return new String[] { "Annotated filter example is here." };
	}
}
