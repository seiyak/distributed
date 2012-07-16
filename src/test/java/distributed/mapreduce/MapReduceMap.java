package distributed.mapreduce;

import java.util.Map;

import distributed.annotation.MapPhase;
import distributed.annotation.SetUp;
import distributed.record.RecordFormat;

@SetUp(numberOfMap = 5,numberOfReduce=4,recordFormat=RecordFormat.PLAIN)
@MapPhase(mapper = MapperImpl.class, mapInput = MapInputImpl.class)
public class MapReduceMap extends AbstractMapReduce<String, Map<String, Integer>> {

	public MapReduceMap(){
		
	}
}
