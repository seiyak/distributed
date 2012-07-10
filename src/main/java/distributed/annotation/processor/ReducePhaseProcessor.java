package distributed.annotation.processor;

import java.lang.annotation.Annotation;

import distributed.annotation.ReducePhase;


public class ReducePhaseProcessor extends AbstractProcessor {
	
	public ReducePhaseProcessor(Object target){
		super(target);
	}
	
	public Object[] process() {
		
		Annotation an = validate(ReducePhase.class);
		if(an == null){
			return null;
		}
		
		ReducePhase reduce = (ReducePhase)an;
		
		Object[] values = new Object[1];
		values[0] = reduce.reducer();
		return values;
	}

}
