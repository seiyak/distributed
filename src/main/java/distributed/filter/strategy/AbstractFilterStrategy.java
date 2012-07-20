package distributed.filter.strategy;

import distributed.filter.helper.reflect.Reflector;

public abstract class AbstractFilterStrategy<I> implements FilterStrategy<I> {

	protected final I start;
	protected final I end;
	protected final Reflector<I> reflector;

	protected AbstractFilterStrategy(I start, I end) {
		this.start = start;
		this.end = end;
		reflector = new Reflector<I>();
	}
}
