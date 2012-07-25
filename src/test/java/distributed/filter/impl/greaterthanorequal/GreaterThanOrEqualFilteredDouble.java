package distributed.filter.impl.greaterthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterDoubleInput;

@FilterPhase(filters = { @Filter(filterName = "greaterThanOrEqualDouble", filter = GreaterThanOrEqualFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "2") }) }, input = LessThanFilterDoubleInput.class)
public class GreaterThanOrEqualFilteredDouble extends AbstractFilter<Double, Double> {

}
