package distributed.filter.impl.tab;

import distributed.annotation.FilterPhase;
import distributed.annotation.Filter;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.TabFilter;
import distributed.annotation.Argument;

@FilterPhase(filters = { @Filter(filterName = "TabFilter", filter = TabFilter.class, arguments = {
		@Argument(value = "true"), @Argument(value = "4") }) }, input = TabFilteredInput.class)
public class TabFilteredSplitTrue extends AbstractFilter<String, String> {

}
