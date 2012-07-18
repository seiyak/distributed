package distributed.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import distributed.annotation.object.FilterObject;
import distributed.annotation.processor.FilterPhaseProcessor;
import distributed.annotation.processor.SetUpProcessor;
import distributed.filter.helper.reflect.Reflector;
import distributed.input.DistributedInput;

public abstract class AbstractFilter<I, R> {

	private final String[] slaves;
	private final String slaveList;
	private final FilterObject[] filterObjects;
	private final Reflector reflector = new Reflector();
	private final DistributedInput<I> input;

	public String[] getSlaves() {
		return slaves;
	}

	public String getSlaveList() {
		return slaveList;
	}

	public FilterObject[] getFilterObjects() {
		return filterObjects;
	}

	@SuppressWarnings("unchecked")
	protected AbstractFilter() {
		Object[] values = new SetUpProcessor( this ).process();
		if ( values == null ) {
			// Use slaves and slaveList from FilterPhase annotation.
			values = new FilterPhaseProcessor( this ).process();
			filterObjects = (FilterObject[]) values[0];
			slaves = (String[]) values[1];
			slaveList = (String) values[2];
			input = (DistributedInput<I>) values[3];
		}
		else {
			// Override slaves and slaveList from FilterPhase with SetUp annotation.
			slaves = (String[]) values[3];
			slaveList = (String) values[4];
			Object[] vals = new FilterPhaseProcessor( this ).process();
			filterObjects = (FilterObject[]) vals[0];
			input = (DistributedInput<I>) vals[3];
		}
	}

	public R[] runFilter() throws Exception {
		I[] in = (I[]) input.getInput();
		R[] result = null;
		for ( Filter<I, R> filter : collectFilters( mapFiltersByFilterName() ) ) {
			//result = (I[]) filter.filter( result[0] );
			
			//TODO how to deal with multiple filters ?
			result = (R[]) filter.filter( in );
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private Filter<I, R>[] collectFilters(Map<String, LinkedList<FilterObject>> filters) throws RuntimeException,
			InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<Filter<I, R>> list = new ArrayList<Filter<I, R>>();
		FilterObject filterObject = null;
		for ( Entry<String, LinkedList<FilterObject>> entry : filters.entrySet() ) {

			if ( entry.getValue().size() == 1 ) {
				filterObject = entry.getValue().get( 0 );
				list.add( reflector.instantiateFilter( filterObject ) );
			}
			else if ( entry.getValue().size() > 1 ) {
				// TODO express composite filters by annotations
				throw new UnsupportedOperationException( "not yet implemented" );
			}
		}

		return (Filter<I, R>[]) list.toArray( new Filter[list.size()] );
	}

	private Map<String, LinkedList<FilterObject>> mapFiltersByFilterName() {

		if ( filterObjects == null ) {
			throw new IllegalArgumentException( "at least one filter must be specified" );
		}

		Map<String, LinkedList<FilterObject>> filters = new HashMap<String, LinkedList<FilterObject>>();
		for ( int i = 0; i < filterObjects.length; i++ ) {
			if ( filters.get( filterObjects[i].getFilerName() ) == null ) {
				// first time to put the filter name
				LinkedList<FilterObject> filterList = new LinkedList<FilterObject>();
				filterList.add( filterObjects[i] );
				filters.put( filterObjects[i].getFilerName(), filterList );
			}
			else if ( filters.get( filterObjects[i].getFilerName() ) != null ) {
				// has seen the filter name. the list is already instantiated, so just put the entry into the map.
				filters.get( filterObjects[i].getFilerName() ).add( filterObjects[i] );
			}
		}

		return filters;
	}
}
