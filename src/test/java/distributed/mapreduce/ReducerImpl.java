package distributed.mapreduce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import distributed.reduce.Reducer;

public class ReducerImpl implements Reducer<Map<String,Integer>> {

	public Map<String, Integer> reduce(String[] keys,List[] values) {
		
		Map<String,Integer> result = new HashMap<String,Integer>();
		for(int i = 0; i < keys.length;i++){
			int count = 0;
			for(int j = 0; j < values[i].size();j++){
				count += (Integer)values[i].get( j );
			}
			result.put( keys[i], count );
		}

		return result;
	}

	public void emit() {
		// TODO Auto-generated method stub
		
	}
}
