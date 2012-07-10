package distributed.annotation.processor;

import java.lang.annotation.Annotation;

import distributed.annotation.MapPhase;


public class MapPhaseProcessor extends AbstractProcessor{
	
	public MapPhaseProcessor(Object target){
		super(target);
	}

	public Object[] process() {
		
		Annotation an = validate(MapPhase.class);
		if(an == null){
			return null;
		}
		MapPhase map = (MapPhase)an;
		Object[] values = new Object[2];
		values[0] = map.mapper();
		values[1] = map.mapInput();
		
		return values;
	}
}
