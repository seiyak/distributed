package distributed.filter.impl.lessthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanFilter;

@FilterPhase(filters = { @Filter(filterName = "lessThanFloat", filter = LessThanFilter.class, arguments = {
		@Argument(value = "5"), @Argument(value = "1.235") }) }, input = LessThanFilterFloatInput.class)
public class LessThanFilteredFloat extends AbstractFilter<Float, Float> {

}
