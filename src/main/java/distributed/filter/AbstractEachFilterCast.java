package distributed.filter;

import java.util.List;

import distributed.filter.helper.reflect.Reflector;

public abstract class AbstractEachFilterCast<O> extends AbstractEachFilterString {
	
	private final Reflector<String> reflector;
	
	public AbstractEachFilterCast(String pattern, boolean split, int numberOfFilter) {
		super( pattern, split, numberOfFilter );
		reflector = new Reflector<String>();
	}

	protected List<O> doFilter(String[] input, O obj) throws Exception {

		return reflector.castFrom( super.filter( input ), obj );
	}
}
