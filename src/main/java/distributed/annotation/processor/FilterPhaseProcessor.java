package distributed.annotation.processor;

import java.lang.annotation.Annotation;

import distributed.annotation.FilterPhase;

public class FilterPhaseProcessor extends AbstractProcessor {

	private final FilterProcessor filterProcessor;

	public FilterPhaseProcessor(Object target) {
		super( target );
		filterProcessor = new FilterProcessor( target );
	}

	public Object[] process() {

		Annotation an = validate( FilterPhase.class );
		if ( an == null ) {
			return null;
		}

		FilterPhase filterPhase = (FilterPhase) an;

		Object[] values = new Object[4];
		try {
			values[0] = filterProcessor.process( filterPhase.filters(), filterPhase.input() );
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		values[1] = filterPhase.slaves();
		values[2] = filterPhase.slaveList();
		try {
			values[3] = filterPhase.input().newInstance();
		}
		catch ( InstantiationException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IllegalAccessException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;
	}

}
