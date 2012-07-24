package distributed.filter.impl.greaterthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.GreaterThanFilter;

@FilterPhase(filters = { @Filter(filterName = "greaterThanInt", filter = GreaterThanFilter.class, arguments = {
		@Argument(value = "2"), @Argument(value = "5") }) }, input = GreaterThanFilterIntInput.class)
public class GreaterThanFilteredInt extends AbstractFilter<Integer, Integer> {

}
