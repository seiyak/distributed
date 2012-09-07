package distributed.filter.impl.integer;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.IntegerFilter;

@FilterPhase(filters = { @Filter(filterName = "IntegerFilter", filter = IntegerFilter.class, arguments = { @Argument(value = "4") }) }, input = IntegerFilteredInput.class)
public class IntegerFilterAnnotated extends AbstractFilter<String, Integer> {

}
