package distributed.annotation;

public @interface Filter {

	String filterName();

	Class<? extends distributed.filter.Filter> filter();

	Argument[] arguments() default {};
}
