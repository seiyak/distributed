package distributed.filter.impl.between;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.BetweenFilter;

@FilterPhase(filters = { @Filter(filterName = "betweenDouble", filter = BetweenFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "0.1"), @Argument(value = "0.4") }) }, input = BetweenFilterDoubleInput.class)
public class BetweenFilteredDouble extends AbstractFilter<Double, Double> {

}
