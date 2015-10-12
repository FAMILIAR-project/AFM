/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
/*
 * Created on 10-Jan-2005
 *
 */
package fr.familiar.attributedfm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;



/**
 * A product is a set of features.
 */
public class Product {
	
	protected String name;
	
	protected Map<VariabilityElement,Object> listOfElemets;
	
	public Map<VariabilityElement, Object> getListOfElemets() {
		return listOfElemets;
	}

	public Product () {
		listOfElemets = new HashMap<VariabilityElement,Object>();
	}
	
	public int getNumberOfElements() {
		return listOfElemets.size();
	}
	
	public void addFeature (Feature f) {
		listOfElemets.put(f,1);
	}
	
	public void addElement (VariabilityElement f,Object i) {
		listOfElemets.put(f,i);
	}
	public Collection<VariabilityElement> getElements(){
		return listOfElemets.keySet();
	}
	
//	public boolean equals(Object p){
//		boolean eq=false;
//		if (p instanceof Product){
//			Collection<? extends VariabilityElement> listOfFeat1=((Product) p).getFeatures();
//			if(listOfFeat1.containsAll(listOfFeatures)&&listOfFeatures.containsAll(listOfFeat1))
//				eq=true;
//		}
//		
//		return eq;
//	}
	
	@Override
	public String toString(){
		Iterator<Entry<VariabilityElement,Object>> it = listOfElemets.entrySet().iterator();
		String str="";
		while  (it.hasNext()){
			Entry<VariabilityElement,Object> entry = it.next();
			String str2="";
			if(entry.getKey() instanceof Feature){
				str2=entry.getKey().getName()+"="+entry.getValue().toString();	
			}else if(entry.getKey() instanceof GenericAttribute){
				GenericAttribute att=(GenericAttribute) entry.getKey();
				str2=att.getFullName()+"="+entry.getValue().toString();
				
			}
			str+=str2+"\r\n";
		
		}
		return str;

		
	}

	public boolean removeFeature(VariabilityElement f,Object o) {
		return listOfElemets.remove(f, o);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
