package distributed.filter.impl.equal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.EqualFilter;

@FilterPhase(filters = { @Filter(filterName = "Equal", filter = EqualFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "false") }) }, input = EqualFilterBooleanInput.class)
public class EqualFilteredBoolean extends AbstractFilter<Boolean, Boolean> {

}
