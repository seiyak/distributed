package distributed.filter.impl;

import distributed.annotation.FilterPhase;
import distributed.annotation.Filter;
import distributed.annotation.Argument;
import distributed.filter.AbstractFilter;

@FilterPhase(filters = { @Filter(filterName = "WhiteSpaceFilter", filter = WhiteSpaceFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "true") }) }, input = FilteredInput.class)
public class FilteredSplitTrue extends AbstractFilter<String>{

}
