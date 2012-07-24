package distributed.filter.impl.lessthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanFilter;

@FilterPhase(filters = { @Filter(filterName = "lessThanBoolean", filter = LessThanFilter.class, arguments = {
		@Argument(value = "6"), @Argument(value = "false") }) }, input = LessThanFilterBooleanInput.class)
public class LessThanFilteredBoolean extends AbstractFilter<Boolean, Boolean> {

}
