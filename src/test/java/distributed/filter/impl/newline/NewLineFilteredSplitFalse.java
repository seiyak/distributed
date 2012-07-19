package distributed.filter.impl.newline;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.NewLineFilter;

@FilterPhase(filters = { @Filter(filterName = "NewLineFilter", filter = NewLineFilter.class, arguments = {
		@Argument(value = "false"), @Argument(value = "4") }) }, input = NewLineFilterInput.class)
public class NewLineFilteredSplitFalse extends AbstractFilter<String, String> {

}
