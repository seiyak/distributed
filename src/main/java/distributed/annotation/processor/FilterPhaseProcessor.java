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

		Object[] values = new Object[5];
		values[0] = filterPhase.numberOfFilter();
		values[1] = filterProcessor.process( filterPhase.filters() );
		values[2] = filterPhase.slaves();
		values[3] = filterPhase.slaveList();
		values[4] = filterPhase.input();

		return values;
	}

}
