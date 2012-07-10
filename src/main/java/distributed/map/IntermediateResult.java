package distributed.map;

public class IntermediateResult {

	private String key;
	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	private Object value;
	
	public IntermediateResult(String key,Object value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(this == obj){
			return true;
		}
		
		if(obj instanceof IntermediateResult){
			IntermediateResult intermediateRes = (IntermediateResult) obj;
			
			return this.key.equals(intermediateRes.getKey()) && this.value.equals(intermediateRes.getValue());
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		return this.getClass().getName() +  " key: " + key + " value: " + value;
	}
}
