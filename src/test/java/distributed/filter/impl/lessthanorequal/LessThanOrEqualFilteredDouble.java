package distributed.filter.impl.lessthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterDoubleInput;

@FilterPhase(filters = { @Filter(filterName = "lessThanOrEqualDouble", filter = LessThanOrEqualFilter.class, arguments = {
		@Argument(value = "5"), @Argument(value = "1") }) }, input = LessThanFilterDoubleInput.class)
public class LessThanOrEqualFilteredDouble extends AbstractFilter<Double, Double> {

}
