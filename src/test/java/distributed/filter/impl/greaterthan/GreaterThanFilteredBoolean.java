package distributed.filter.impl.greaterthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanFilter;

@FilterPhase(filters = { @Filter(filterName = "greaterThanBoolean", filter = GreaterThanFilter.class, arguments = {
		@Argument(value = "6"), @Argument(value = "true") }) }, input = GreaterThanFilterBooleanInput.class)
public class GreaterThanFilteredBoolean extends
		AbstractFilter<Boolean, Boolean> {

}
