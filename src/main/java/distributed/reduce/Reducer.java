package distributed.reduce;

import java.util.List;

public interface Reducer<R> {

	public R reduce(String[] keys, List<Object>[] values);

	public void emit();
}
