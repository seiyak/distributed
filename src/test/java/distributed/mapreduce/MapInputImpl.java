	package distributed.mapreduce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import distributed.input.DistributedInput;

public class MapInputImpl implements DistributedInput {

	public Object[] getInput() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream( "TestInput1" )));
		String str = "";
		List<String> l = new ArrayList<String>();
		try {
			while((str = br.readLine()) != null){
				String[] each = str.split( "(\\p{Space})" );
				
				//TODO construct filter and use it if necessary
				for(String s: each){
					l.add( s );
				}
			}
		}
		catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l.toArray( new String[l.size()] );
	}
}
