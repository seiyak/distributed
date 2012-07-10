package distributed.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import distributed.input.DistributedInput;
import distributed.map.Mapper;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapPhase {
	
	Class<? extends Mapper> mapper();
	Class<? extends DistributedInput> mapInput();
}
