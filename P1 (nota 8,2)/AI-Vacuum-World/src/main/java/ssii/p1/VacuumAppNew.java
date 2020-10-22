package ssii.p1;

import ssii.p1.actions.Location;
import ssii.p1.world.VacuumWorld;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
//import aima.core.search.uninformed.*;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class VacuumAppNew {
	public static void main(String args[]) throws InterruptedException {
		Location vacuumloc = new Location(0, 0);
		int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 5, 5, 0, 0, 0, 0, 0, 0 }, 
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }	
		}; 
		VacuumWorld env = new VacuumWorld(vacuumloc, worldSquares);
		env.addAgent(new VacuumSearchAgent(qs-> new IterativeDeepeningSearch()));
		System.out.println(env.getCurrentState());
		while (true) {
			env.step();
			System.out.println(env.getCurrentState());
			Thread.sleep(1000);
			VacuumState envState = (VacuumState) env.getCurrentState();
			if (envState.getGlobalDirtCount() == 0) {
				envState.addRandomDirt();
				System.out.println(env.getCurrentState());
			}
		}
	}
}
