package fr.familiar.attributedfm.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class IntegerRange extends Range{

	public Collection<Integer> items;
	
	public IntegerRange(Collection<Integer> integers) {
		this.items=integers;
	}

	public IntegerRange() {
		this.items=new LinkedList<Integer>();
	}
	
	@Override
	public Object getMax() {
		return Collections.max(items);
	}

	@Override
	public Object getMin() {
		return Collections.min(items);

	}

	@Override
	public Collection<Integer> getItems() {
		return items;
	}

	@Override
	public void add(Object o) {
		if(o instanceof Integer){
			this.items.add((Integer)o);
		}else{
			throw new IllegalStateException("Adding a non integer where it does not fit");
		}
	}
	


}
