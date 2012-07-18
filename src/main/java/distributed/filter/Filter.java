package distributed.filter;

public interface Filter<I, R> {

	public R[] filter(I[] input) throws Exception;
}
