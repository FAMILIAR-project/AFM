package fr.familiar.attributedfm.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class RealRange extends Range{

	private Collection<Float> items;
	
	public RealRange(Collection<Float> floats) {
		this.items=floats;
	}
	
	public RealRange() {
		items= new LinkedList<Float>();
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
	public Collection<Float> getItems() {
		return items;
	}

	@Override
	public void add(Object o) {
		if(o instanceof Float){
			this.items.add((Float)o);
		}else{
			throw new IllegalStateException("Adding a non integer where it does not fit");
		}		
	}
	
}
