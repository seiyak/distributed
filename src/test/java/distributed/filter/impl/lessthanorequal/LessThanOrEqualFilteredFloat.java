package distributed.filter.impl.lessthanorequal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanOrEqualFilter;
import distributed.filter.impl.lessthan.LessThanFilterFloatInput;

@FilterPhase(filters = { @Filter(filterName = "lessThanOrEqualFloat", filter = LessThanOrEqualFilter.class, arguments = {
		@Argument(value = "2"), @Argument(value = "1.24") }) }, input = LessThanFilterFloatInput.class)
public class LessThanOrEqualFilteredFloat extends AbstractFilter<Float, Float> {

}
