package distributed.filter.impl.between;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.BetweenFilter;

@FilterPhase(filters = { @Filter(filterName = "betweenFloat", filter = BetweenFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "0.01"), @Argument(value = "0.01") }) }, input = BetweenFilterFloatInput.class)
public class BetweenFilteredFloat extends AbstractFilter<Float, Float> {

}
