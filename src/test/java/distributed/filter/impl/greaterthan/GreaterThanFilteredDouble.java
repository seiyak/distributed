package distributed.filter.impl.greaterthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanFilter;

@FilterPhase(filters = { @Filter(filterName = "greaterThanDouble", filter = GreaterThanFilter.class, arguments = {
		@Argument(value = "2"), @Argument(value = "2.1") }) }, input = GreaterThanFilterDoubleInput.class)
public class GreaterThanFilteredDouble extends AbstractFilter<Double, Double> {

}
