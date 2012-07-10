package distributed.map;

public interface Mapper {

	public IntermediateResult[] map(Object[] input);
	public void emit(IntermediateResult[] intermediateResults);
}
