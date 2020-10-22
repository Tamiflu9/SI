package ssii.p2.agent;

import aima.core.search.framework.HeuristicFunction;
import ssii.p1.state.VacuumState;

public class GlobalDirtAndDistanceHeuristic implements HeuristicFunction {
	@Override
	public double h(Object state) {
		VacuumState vs=(VacuumState)state;
		int distance=0;
		for (int x=0;x<vs.getWidth();x++)
			for (int y=0;y<vs.getHeight();y++)
				if (vs.getDirtAt(x,y)>0){
					distance=distance+
							(x-vs.getLocation().getX())*(x-vs.getLocation().getX())+
							(y-vs.getLocation().getY())*(y-vs.getLocation().getY());
				}
		return vs.getGlobalDirtCount()+distance;
	}
}
