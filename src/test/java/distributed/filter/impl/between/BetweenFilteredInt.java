package distributed.filter.impl.between;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.BetweenFilter;

@FilterPhase(filters = { @Filter(filterName = "between", filter = BetweenFilter.class, arguments = {
		@Argument(value = "1"), @Argument(value = "10"), @Argument(value = "15") }) }, input = BetweenFilterIntInput.class)
public class BetweenFilteredInt extends AbstractFilter<Integer, Integer> {

}
