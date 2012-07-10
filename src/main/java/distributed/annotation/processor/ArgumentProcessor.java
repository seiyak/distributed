package distributed.annotation.processor;

import java.lang.annotation.Annotation;

import distributed.annotation.Argument;

public class ArgumentProcessor extends AbstractProcessor {

	public ArgumentProcessor(Object target) {
		super( target );
	}

	public Object[] process() {

		Annotation an = validate( Argument.class );
		if ( an == null ) {
			return null;
		}

		Argument argument = (Argument) an;
		Object[] values = new Object[1];
		values[0] = argument.value();

		return values;
	}

	public String[] process(Argument[] arguments) {

		if ( arguments == null || arguments.length == 0 ) {
			return new String[] {};
		}

		String[] values = new String[arguments.length];
		for ( int i = 0; i < arguments.length; i++ ) {
			values[i] = arguments[i].value();
		}

		return values;
	}
}
