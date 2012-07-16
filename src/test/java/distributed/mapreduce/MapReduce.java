package distributed.mapreduce;

import java.util.Map;

import distributed.annotation.MapPhase;
import distributed.annotation.ReducePhase;
import distributed.annotation.SetUp;
import distributed.mapreduce.AbstractMapReduce;
import distributed.record.RecordFormat;


@SetUp(numberOfMap = 3,numberOfReduce=4,recordFormat=RecordFormat.PLAIN)
@MapPhase(mapper = MapperImpl.class, mapInput = MapInputImpl.class)
@ReducePhase(reducer = ReducerImpl.class)
public class MapReduce extends AbstractMapReduce<String, Map<String, Integer>> {

	/**
	 * @param target
	 */
	public MapReduce() {

	}
}
