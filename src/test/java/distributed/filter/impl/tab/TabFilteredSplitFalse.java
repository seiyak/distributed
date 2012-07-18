package distributed.filter.impl.tab;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.TabFilter;

@FilterPhase(filters = { @Filter(filterName = "TabFilter", filter = TabFilter.class, arguments = {
	@Argument(value = "4"), @Argument(value = "false") }) }, input = TabFilteredInput.class)
public class TabFilteredSplitFalse extends AbstractFilter<String, String> {

}
