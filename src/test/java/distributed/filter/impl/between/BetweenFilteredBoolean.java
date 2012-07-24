package distributed.filter.impl.between;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.BetweenFilter;

@FilterPhase(filters = { @Filter(filterName = "betweenBoolean", filter = BetweenFilter.class, arguments = {
		@Argument(value = "10"), @Argument(value = "1"), @Argument(value = "2") }) }, input = BetweenFilterBooleanInput.class)
public class BetweenFilteredBoolean extends AbstractFilter<Boolean, Boolean> {

}
