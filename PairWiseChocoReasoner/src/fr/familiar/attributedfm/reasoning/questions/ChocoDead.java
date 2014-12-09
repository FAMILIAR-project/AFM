package fr.familiar.attributedfm.reasoning.questions;

import java.util.Collection;
import java.util.LinkedList;

import static choco.Choco.eq;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.ContradictionException;
import choco.kernel.solver.Solver;
import es.us.isa.ChocoReasoner.pairwise.ChocoQuestion;
import fr.familiar.attributedfm.Feature;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.FeatureModelReasoner;
import fr.familiar.attributedfm.reasoning.PerformanceResult;

public class ChocoDead extends ChocoQuestion {

	public Collection<Feature> deads = new LinkedList<Feature>();

	public PerformanceResult answer(FeatureModelReasoner r) {
		ChocoReasoner reasoner = (ChocoReasoner) r;

		for(Feature f:reasoner.getAllFeatures()){
			Model p = reasoner.getProblem();
			IntegerVariable integerVariable = reasoner.getVariables().get(f.getName());
			Constraint q = eq(integerVariable,1);
			p.addConstraint(q);
			
			Solver s = new CPSolver();
			s.read(p);
			try {
				s.propagate();
			} catch (ContradictionException e) {
				this.deads.add(f);
			}
			p.removeConstraint(q);
		}
		return null;
	}
}
