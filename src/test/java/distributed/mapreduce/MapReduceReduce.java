package distributed.mapreduce;

import java.util.Map;

import distributed.annotation.ReducePhase;
import distributed.annotation.SetUp;
import distributed.record.RecordFormat;

@SetUp(numberOfMap = 3,numberOfReduce=4,recordFormat=RecordFormat.PLAIN)
@ReducePhase(reducer = ReducerImpl.class)
public class MapReduceReduce extends AbstractMapReduce<Map<String, Integer>> {

	public MapReduceReduce(){
		
	}
}
