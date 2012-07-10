package distributed.filter;

public interface Filter<T> {

	public T[] filter(T input) throws Exception;
}
