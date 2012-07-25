package distributed.filter.impl.greaterthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterIntInput;

@FilterPhase(filters = { @Filter(filterName = "greaterThanOrEqualInt", filter = GreaterThanOrEqualFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "60") }) }, input = LessThanFilterIntInput.class)
public class GreaterThanOrEqualFilteredInt extends AbstractFilter<Integer, Integer> {

}
