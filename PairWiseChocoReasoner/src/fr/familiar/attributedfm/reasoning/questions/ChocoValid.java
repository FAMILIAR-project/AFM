package fr.familiar.attributedfm.reasoning.questions;

import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.solver.ContradictionException;
import choco.kernel.solver.Solver;
import es.us.isa.ChocoReasoner.pairwise.ChocoQuestion;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.FeatureModelReasoner;
import fr.familiar.attributedfm.reasoning.PerformanceResult;

public class ChocoValid extends ChocoQuestion {

	boolean valid = true;

	public PerformanceResult answer(FeatureModelReasoner r) {
		ChocoReasoner reasoner = (ChocoReasoner) r;

		Model p = reasoner.getProblem();

		Solver s = new CPSolver();
		s.read(p);
		try {
			s.propagate();
		} catch (ContradictionException e) {
			this.valid = false;
		}

		return null;
	}
}
