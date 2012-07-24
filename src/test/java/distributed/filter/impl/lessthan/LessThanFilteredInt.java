package distributed.filter.impl.lessthan;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.LessThanFilter;

@FilterPhase(filters = { @Filter(filterName = "lessThanInt", filter = LessThanFilter.class, arguments = {
		@Argument(value = "2"), @Argument(value = "60") }) }, input = LessThanFilterIntInput.class)
public class LessThanFilteredInt extends AbstractFilter<Integer, Integer> {

}
