package distributed.filter.impl.whitespace;

import distributed.annotation.FilterPhase;
import distributed.annotation.Filter;
import distributed.annotation.Argument;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.WhiteSpaceFilter;

@FilterPhase(filters = { @Filter(filterName = "WhiteSpaceFilter", filter = WhiteSpaceFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "true") }) }, input = WhiteSpaceFilteredInput.class)
public class WhiteSpaceFilteredSplitTrue extends AbstractFilter<String>{

}
