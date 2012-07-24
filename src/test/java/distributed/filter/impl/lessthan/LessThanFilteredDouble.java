package distributed.filter.impl.lessthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanFilter;

@FilterPhase(filters = { @Filter(filterName = "lessThanDouble", filter = LessThanFilter.class, arguments = {
		@Argument(value = "5"), @Argument(value = "1.23") }) }, input = LessThanFilterDoubleInput.class)
public class LessThanFilteredDouble extends AbstractFilter<Double, Double> {

}
