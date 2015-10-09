package fr.familiar.attributedfm.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StringRange extends IntegerRange {

	private Map<Integer,String> strings;
	
	public StringRange (){
		this.strings= new HashMap<Integer,String>();
	}
	
	public StringRange (Collection strings){
		this.strings= new HashMap<Integer,String>();	
		int i=1;
		for(Object str:strings){
			this.strings.put(i, str.toString());
		}
		
	}
	@Override
	public Collection<Integer> getItems() {
		return this.strings.keySet();
	}
	
	@Override
	public void add(Object o) {
		if(o instanceof String){
			this.addString((String)o);
		
		}else{
			throw new IllegalStateException("Adding a non string where it does not fit");
		}
	}

	private void addString(String str){
		this.strings.put(strings.size()+1, str);
	}
	
	public String getString(int i){
		return strings.get(i);
	}

}
