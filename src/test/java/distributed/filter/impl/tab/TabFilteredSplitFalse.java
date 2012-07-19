package distributed.filter.impl.tab;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.TabFilter;

@FilterPhase(filters = { @Filter(filterName = "TabFilter", filter = TabFilter.class, arguments = {
	@Argument(value = "false"), @Argument(value = "4") }) }, input = TabFilteredInput.class)
public class TabFilteredSplitFalse extends AbstractFilter<String, String> {

}
