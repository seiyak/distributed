package distributed.filter.impl.equal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.EqualFilter;
import distributed.filter.impl.equal.EqaulFilterIntInput;

@FilterPhase(filters = { @Filter(filterName = "Equal", filter = EqualFilter.class, arguments = {
		@Argument(value = "5"), @Argument(value = "1") }) }, input = EqaulFilterIntInput.class)
public class EqualFilteredInt extends AbstractFilter<Integer, Integer> {

}
