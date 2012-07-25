package distributed.filter.impl.lessthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterBooleanInput;

@FilterPhase(filters = { @Filter(filterName = "lessThanOrEqualBoolean", filter = LessThanOrEqualFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "false") }) }, input = LessThanFilterBooleanInput.class)
public class LessThanOrEqualFilteredBoolean extends AbstractFilter<Boolean, Boolean> {

}
