package distributed.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import distributed.record.RecordFormat;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SetUp {

	int numberOfMap() default 1;
	int numberOfReduce() default 1;
	int maxTrial() default 1;
	String[] slaves() default {};
	String slaveList() default "";
	String recordFormat() default RecordFormat.XML;
}
