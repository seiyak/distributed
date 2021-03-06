package distributed.mapreduce;

import distributed.annotation.ReducePhase;
import distributed.annotation.SetUp;
import distributed.record.RecordFormat;

@SetUp(numberOfMap = 3,numberOfReduce=4,recordFormat=RecordFormat.PLAIN)
@ReducePhase(reducer = ReducerImpl.class)
public class MapReduceReduce extends AbstractMapReduce<String, String, Integer> {

	public MapReduceReduce(){
		
	}
}
