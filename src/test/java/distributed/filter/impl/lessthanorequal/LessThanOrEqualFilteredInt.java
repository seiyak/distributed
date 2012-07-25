package distributed.filter.impl.lessthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterIntInput;

@FilterPhase(filters = { @Filter(filterName = "lessThanOrEqualInt", filter = LessThanOrEqualFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "20") }) }, input = LessThanFilterIntInput.class)
public class LessThanOrEqualFilteredInt extends AbstractFilter<Integer, Integer> {

}
