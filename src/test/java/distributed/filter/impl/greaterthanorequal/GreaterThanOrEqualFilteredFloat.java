package distributed.filter.impl.greaterthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterFloatInput;

@FilterPhase(filters = { @Filter(filterName = "greaterThanOrEqualFlot", filter = GreaterThanOrEqualFilter.class, arguments = {
		@Argument(value = "6"), @Argument(value = "1.2") }) }, input = LessThanFilterFloatInput.class)
public class GreaterThanOrEqualFilteredFloat extends AbstractFilter<Float, Float> {

}
