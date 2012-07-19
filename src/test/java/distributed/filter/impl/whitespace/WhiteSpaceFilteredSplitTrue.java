package distributed.filter.impl.whitespace;

import distributed.annotation.FilterPhase;
import distributed.annotation.Filter;
import distributed.annotation.Argument;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.WhiteSpaceFilter;

@FilterPhase(filters = { @Filter(filterName = "WhiteSpaceFilter", filter = WhiteSpaceFilter.class, arguments = {
		@Argument(value = "true"), @Argument(value = "4") }) }, input = WhiteSpaceFilteredInput.class)
public class WhiteSpaceFilteredSplitTrue extends AbstractFilter<String, String>{

}
