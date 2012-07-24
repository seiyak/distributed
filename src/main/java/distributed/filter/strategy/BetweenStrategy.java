package distributed.filter.strategy;

public class BetweenStrategy<I> extends AbstractFilterStrategy<I> {

	public BetweenStrategy(I start, I end) {
		super( start, end );
	}

	public boolean compare(I compared) throws Exception {

		if ( reflector.isBoolean( compared ) ) {
			throw new IllegalArgumentException( "specified type is Boolean. Boolean can't operate on between filter." );

		}

		if ( ( reflector.compareTo( start, compared ) == 0 || reflector.compareTo( start, compared ) < 0 )
				&& ( reflector.compareTo( end, compared ) == 0 || reflector.compareTo( end, compared ) > 0 ) ) {
			return true;
		}

		return false;
	}
}
