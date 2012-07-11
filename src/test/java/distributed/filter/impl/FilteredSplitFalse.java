package distributed.filter.impl;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;

@FilterPhase(filters = { @Filter(filterName = "WhiteSpaceFilter", filter = WhiteSpaceFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "false") }) }, input = FilteredInput.class)
public class FilteredSplitFalse extends AbstractFilter<String> {

}
