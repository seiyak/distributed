package distributed.filter;

import java.util.List;

public interface Filter<I, R> {

	public List<R> filter(I[] input) throws Exception;
}
