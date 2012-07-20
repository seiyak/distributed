package distributed.filter.impl.equal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.EqualFilter;

@FilterPhase(filters = { @Filter(filterName = "EqualDouble", filter = EqualFilter.class, arguments = {
		@Argument(value = "2"), @Argument(value = "0.1234") }) }, input = EqualFilterDoubleInput.class)
public class EqualFilteredDouble extends AbstractFilter<Double, Double> {

}
