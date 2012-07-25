package distributed.filter.impl.greaterthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterBooleanInput;

@FilterPhase(filters = { @Filter(filterName = "greaterThanOrEqualBoolean", filter = GreaterThanOrEqualFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "false") }) }, input = LessThanFilterBooleanInput.class)
public class GreaterThanOrEqualFilteredBoolean extends AbstractFilter<Boolean, Boolean> {

}
