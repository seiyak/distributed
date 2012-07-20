package distributed.filter.strategy;

public interface FilterStrategy<I> {

	public boolean compare(I compared) throws Exception;
}
