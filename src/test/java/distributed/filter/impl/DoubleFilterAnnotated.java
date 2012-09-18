package distributed.filter.impl;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;

@FilterPhase(filters = { @Filter(filterName = "DoubleFilter", filter = DoubleFilter.class, arguments = { @Argument(value = "4") }) }, input = DoubleFilteredInput.class)
public class DoubleFilterAnnotated extends AbstractFilter<String, Double> {

}
