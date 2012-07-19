package distributed.filter.impl.whitespace;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.WhiteSpaceFilter;

@FilterPhase(filters = { @Filter(filterName = "WhiteSpaceFilter", filter = WhiteSpaceFilter.class, arguments = {
		@Argument(value = "false"), @Argument(value = "4") }) }, input = WhiteSpaceFilteredInput.class)
public class WhiteSpaceFilteredSplitFalse extends AbstractFilter<String, String> {

}
