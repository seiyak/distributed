package distributed.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import distributed.input.DistributedInput;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FilterPhase {

	Filter[] filters() default {};

	String[] slaves() default {};

	String slaveList() default "";

	Class<? extends DistributedInput> input();
}
