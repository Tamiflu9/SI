package ssii.p2.agent;

import aima.core.search.framework.HeuristicFunction;
import ssii.p1.state.VacuumState;

public class HeuristicaManhattan implements HeuristicFunction{

		@Override
		public double h(Object state) {
			VacuumState vs=(VacuumState)state;
			int distance = 0;
			for(int i = 0; i < vs.getWidth(); i++) {
				for(int j = 0; j < vs.getHeight(); j++) {
					if(vs.getDirtAt(i, j) > 0) {
						distance += java.lang.Math.abs(((i-vs.getLocation().getX())+(i-vs.getLocation().getX())-(j-vs.getLocation().getY())+(j-vs.getLocation().getY())));
					}
				}
			}
			return distance;
		}
	

}
