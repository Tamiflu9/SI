package ssii.p2.agent;

import aima.core.search.framework.HeuristicFunction;
import ssii.p1.state.VacuumState;

public class GlobalDirtHeuristic implements HeuristicFunction {

	@Override
	public double h(Object state) {
		VacuumState vs=(VacuumState)state;						 
		return vs.getGlobalDirtCount();
	}
}
