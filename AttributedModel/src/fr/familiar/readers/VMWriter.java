/**
 * 	This file is part of FaMaTS.
 *
 *     FaMaTS is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     FaMaTS is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.readers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import es.us.isa.FAMA.models.variabilityModel.parsers.IWriter;
import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.GenericAttribute;
import fr.familiar.attributedfm.Relation;
import fr.familiar.attributedfm.domain.Domain;
import fr.familiar.attributedfm.domain.IntegerRange;
import fr.familiar.attributedfm.domain.Range;
import fr.familiar.attributedfm.domain.RealRange;
import fr.familiar.attributedfm.domain.StringRange;

public class VMWriter implements IWriter {

	private BufferedWriter writer = null;
	private AttributedFeatureModel fm = null;

	public void writeFile(String fileName, AttributedFeatureModel vm)
			throws Exception {

		File file = new File(fileName);
		fm = (AttributedFeatureModel) vm;

		writer = new BufferedWriter(new FileWriter(file));

		// print tree
		writer.write("Relationships:\r\n");
		generateTree(fm.getRoot());
		writer.write("\r\nAttributes:\r\n");
		generateAttributes(fm.getRoot());
		writer.write("\r\nConstraints:\r\n");
		generateConstratins();
		writer.flush();
		writer.close();

	}

	private void generateConstratins() throws IOException {
		for (Constraint c : fm.getConstraints()) {
			writer.write(c.toString() + "\r\n");
		}

	}

	private void generateAttributes(Feature root) throws IOException {
		
		for(GenericAttribute att:root.getAttributes()){
			if(att.nonDesicion){
				writer.write("@ND ");
			}
			Domain domain = att.getDomain();
			for (Range r:domain.getRanges()){
				if(r instanceof IntegerRange){
					writer.write("int "+root.getName()+"."+att.getName());
					if(r.getItems().size()==1){
						writer.write(" = "+r.getMin());
					}else if(r.getItems().size()==2){
						writer.write(" ["+r.getMin()+".."+r.getMax()+"] ");
					}else{
						writer.write(" [ ");
						for(Object ir:r.getItems()){
							writer.write(ir+",");
						}
						writer.write(" ] ");

					}
					
					
				}else if(r instanceof StringRange){
					writer.write("enum "+root.getName()+"."+att.getName());
					if(r.getItems().size()==1){
						writer.write(" = "+r.getMin());
					}else if(r.getItems().size()==2){
						writer.write(" ["+r.getMin()+".."+r.getMax()+"] ");
					}else{
						writer.write(" [ ");
						for(Object ir:r.getItems()){
							writer.write(ir+",");
						}
						writer.write(" ] ");
				}
				}else if(r instanceof RealRange){
									
						writer.write("real "+root.getName()+"."+att.getName());
						if(r.getItems().size()==1){
							writer.write(" = "+r.getMin());
						}else if(r.getItems().size()==2){
							writer.write(" ["+r.getMin()+".."+r.getMax()+"] ");
						}else{
							writer.write(" [ ");
							for(Object ir:r.getItems()){
								writer.write(ir+",");
							}
							writer.write(" ] ");
						
				}

				writer.write("default "+att.getDefaultValue()+"\r\n");
			}

		}
		Iterator<Relation> relations = root.getRelations();
		while(relations.hasNext()){
			Relation next = relations.next();
			Iterator<Feature> destination = next.getDestination();
			while(destination.hasNext()){
				Feature next2 = destination.next();
				generateAttributes(next2);
			}
		}}
		
	}

	private void generateTree(Feature root) throws IOException {
		writer.write(root.getName());
		if (root.getNumberOfRelations() > 0) {
			writer.write(" { \r\n");
		} else {
			writer.write("\r\n");
		}
		Iterator<Relation> relIt = root.getRelations();
		while (relIt.hasNext()) {
			Relation rel = relIt.next();

			if (rel.isMandatory()) {
				generateTree(rel.getDestinationAt(0));
			} else if (rel.isOptional()) {
				writer.write("? ");
				generateTree(rel.getDestinationAt(0));
			} else if (rel.isAlternative()) {
				writer.write(" someOf {");
				Iterator<Feature> destination = rel.getDestination();
				while (destination.hasNext()) {
					generateTree(destination.next());
				}
				writer.write("}");
			} else if (rel.isOr()) {
				writer.write(" oneOf {");
				Iterator<Feature> destination = rel.getDestination();
				while (destination.hasNext()) {
					generateTree(destination.next());
				}
				writer.write("}");
			}
		}
		if (root.getNumberOfRelations() > 0) {
			writer.write(" }\r\n");
		}

	}

}
