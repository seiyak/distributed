package distributed.filter.impl.greaterthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanFilter;

@FilterPhase(filters = { @Filter(filterName = "greaterThanFloat", filter = GreaterThanFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "0.001") }) }, input = GreaterThanFilterFloatInput.class)
public class GreaterThanFilteredFloat extends AbstractFilter<Float, Float> {

}
