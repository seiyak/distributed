package distributed.mapreduce;

import java.util.ArrayList;
import java.util.Map;


import org.apache.log4j.Logger;

import distributed.annotation.processor.SetUpProcessor;
import distributed.record.RecordFormat;
import distributed.reduce.Reduce;

public abstract class AbstractMapReduce<I, RK, RV> {

	private static Logger log = Logger.getLogger( AbstractMapReduce.class );
	private final int maxTrial;
	private final String[] slaves;
	private final String slaveList;
	private final String recordFormat;
	private final SetUpProcessor setUpProcessor = new SetUpProcessor( this );
	private static final String JDBC_PREFIX = "jdbc:";

	private final distributed.map.Map<I, RV> mapPhase;
	private final Reduce<RV, Map<RK, RV>> reducePhase;

	protected AbstractMapReduce() {
		Object[] values = setUpProcessor.process();
		if ( values != null ) {
			maxTrial = (Integer) values[2];
			slaves = (String[]) values[3];
			slaveList = (String) values[4];
			recordFormat = (String) values[5];
			mapPhase = new distributed.map.Map<I, RV>( this, (Integer) values[0] );
			reducePhase = new Reduce<RV, Map<RK, RV>>(this, (Integer) values[1]);
		}
		else {
			maxTrial = 3;
			slaves = new String[] {};
			slaveList = "";
			recordFormat = RecordFormat.XML;
			mapPhase = new distributed.map.Map<I, RV>( this, 1 );
			reducePhase = new Reduce<RV, Map<RK, RV>>( this, 1 );
		}
	}

	public int getMaxTrial() {
		return maxTrial;
	}

	public String[] getSlaves() {
		return slaves;
	}

	public String getSlaveList() {
		return slaveList;
	}

	public Map<String, ArrayList<RV>> runMapPahse() throws Exception {

		return mapPhase.runMapPahse();
	}

	public Map<RK,RV> runReducePhase(Map<String, ArrayList<RV>> resultFromMapPhase, Map<RK,RV> result) throws Exception {
		return reducePhase.runReducePhase( resultFromMapPhase, result );
	}
	
	public Map<RK,RV> runMapReduce(Map<RK,RV> result) throws Exception{
		return reducePhase.runReducePhase( mapPhase.runMapPahse(),result);
	}
}
