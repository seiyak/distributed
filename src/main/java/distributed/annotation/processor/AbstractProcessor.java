package distributed.annotation.processor;

import java.lang.annotation.Annotation;

public abstract class AbstractProcessor implements Processor{

	protected final Object target;
	
	protected AbstractProcessor(Object target){
		this.target = target;
	}
	
	protected Annotation validate(Class annotation){
		
		if(target.getClass().getAnnotations().length == 0){
			return null;
		}
		
		Annotation an = target.getClass().getAnnotation( annotation );
		if(an == null){
			return null;
		}
		
		return an;
	}
}
