package distributed.filter.impl.equal;

import distributed.annotation.Argument;
import distributed.annotation.Filter;
import distributed.annotation.FilterPhase;
import distributed.filter.AbstractFilter;
import distributed.filter.impl.EqualFilter;

@FilterPhase(filters = { @Filter(filterName = "Equal", filter = EqualFilter.class, arguments = {
		@Argument(value = "4"), @Argument(value = "0.001") }) }, input = EqualFilterFloatInput.class)
public class EqualFilteredFloat extends AbstractFilter<Float, Float> {

}
