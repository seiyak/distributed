package distributed.filter.strategy;

public class EqualStrategy<I> extends AbstractFilterStrategy<I> {

	public EqualStrategy(I start) {
		super(start, start);
	}

	public boolean compare(I compared) throws Exception {

		if (reflector.compareTo(start, compared) == 0) {
			return true;
		}

		return false;
	}

}
