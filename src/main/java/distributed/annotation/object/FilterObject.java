package distributed.annotation.object;

public class FilterObject {

	private final String filerName;
	private final Class<? extends distributed.filter.Filter> filter;
	private final String[] arguments;
	private final Class inputType;

	public FilterObject(String filterName, Class<? extends distributed.filter.Filter> filter, String[] arguments, Class inputType) {
		this.filerName = filterName;
		this.filter = filter;
		this.arguments = arguments;
		this.inputType = inputType;
	}

	/**
	 * @return the filerName
	 */
	public String getFilerName() {
		return filerName;
	}

	/**
	 * @return the filter
	 */
	public Class<? extends distributed.filter.Filter> getFilter() {
		return filter;
	}

	/**
	 * @return the arguments
	 */
	public String[] getArguments() {
		return arguments;
	}

	public Class getInputType() {
		return inputType;
	}
}
