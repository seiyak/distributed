package distributed.filter.strategy;

public class GreaterThanStrategy<I> extends AbstractFilterStrategy<I> {

	public GreaterThanStrategy(I start) {
		super(start, start);
	}

	public boolean compare(I compared) throws Exception {

		if (reflector.isBoolean(compared)) {
			throw new IllegalArgumentException(
					"specified type is Boolean. Boolean can't operate on between filter.");

		}

		if (reflector.compareTo(start, compared) < 0) {
			return true;
		}

		return false;
	}

}
