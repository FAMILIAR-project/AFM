package fr.familiar.attributedfm.reasoning.questions;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;
import choco.kernel.solver.variables.integer.IntDomainVar;
import es.us.isa.ChocoReasoner.pairwise.ChocoQuestion;
import fr.familiar.attributedfm.Product;
import fr.familiar.attributedfm.VariabilityElement;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.FeatureModelReasoner;
import fr.familiar.attributedfm.reasoning.PerformanceResult;

public class ChocoProducts extends ChocoQuestion{

	public ChocoProducts() {	}
	
	
	


	
	public PerformanceResult answer(FeatureModelReasoner choco) {
		
		ChocoReasoner r = (ChocoReasoner)choco;
		//BEFORE USE THE QUESTION WE MARK THE ATRIBUTES AS NO DECISION VAR
		Iterator<IntegerVariable> it=r.getAttributesVariables().values().iterator();
		while(it.hasNext()){
			IntegerVariable var= it.next();
			var.addOption("cp:no_decision");
		}
		Model chocoProblem = r.getProblem();
		Solver solver = new CPSolver();
		solver.read(chocoProblem);
		if (solver.solve() == Boolean.TRUE && solver.isFeasible()) {
			  do {
				  Product p = new Product();
				  for(int i = 0; i < chocoProblem.getNbIntVars(); i ++) {
					  IntDomainVar aux = solver.getVar(chocoProblem.getIntVar(i));
					  if (aux.getVal() > 0){
						  VariabilityElement f = getFeature(aux,r);
						  if (f != null){
							  p.addElement(f,aux.getVal());
						  }
					  }
				  }
				  System.out.println(p);
;
			  } while(solver.nextSolution() == Boolean.TRUE);
			}

			return null;
		}
	
	private VariabilityElement getFeature(IntDomainVar aux, ChocoReasoner reasoner) {
	//	String temp=new String(aux.toString().substring(0,aux.toString().indexOf(":")));
		VariabilityElement f = reasoner.searchFeatureOrAttibuteByName(aux.getName());
		return f;
	}

	

	

}
