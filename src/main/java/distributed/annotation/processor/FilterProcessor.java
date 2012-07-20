package distributed.annotation.processor;

import java.lang.annotation.Annotation;

import distributed.annotation.Filter;
import distributed.annotation.object.FilterObject;
import distributed.filter.helper.reflect.Reflector;
import distributed.input.DistributedInput;

public class FilterProcessor extends AbstractProcessor {

	private final ArgumentProcessor argumentProcessor;
	private final Reflector reflector;

	public FilterProcessor(Object target) {
		super( target );
		argumentProcessor = new ArgumentProcessor( target );
		reflector = new Reflector();
	}

	public Object[] process() {

		Annotation an = validate( Filter.class );
		if ( an == null ) {
			return null;
		}

		Filter filter = (Filter) an;

		Object[] values = new Object[3];
		values[0] = filter.filterName();
		values[1] = filter.filter();
		values[2] = argumentProcessor.process( filter.arguments() );

		return values;
	}

	public FilterObject[] process(Filter[] filters, Class<? extends DistributedInput> input) throws SecurityException, NoSuchMethodException {

		if ( filters == null || filters.length == 0 ) {
			return new FilterObject[] {};
		}

		FilterObject[] filterObjects = new FilterObject[filters.length];
		for ( int i = 0; i < filters.length; i++ ) {
			filterObjects[i] = new FilterObject( filters[i].filterName(), filters[i].filter(),
					argumentProcessor.process( filters[i].arguments() ), reflector.guessFilterArgumentType(input) );
		}

		return filterObjects;
	}
}
