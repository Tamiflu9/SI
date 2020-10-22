package ssii.p3;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Search;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.Scheduler;
import aima.core.search.local.SimulatedAnnealingSearch;
import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
import ssii.p2.agent.GlobalDirtAndDistanceHeuristic;
import ssii.p2.agent.GlobalDirtHeuristic;
import ssii.p3.agent.LinearScheduler;

public class SimmulatedAnnealingTestModificado {

	private VacuumState executePlan(VacuumState initialState, List<Action> actions) {
		System.out.println("Executing plan = " + actions);
		VacuumState currentState = initialState;
		for (Action action:actions) {
			if (!action.isNoOp()) {
				OOAction oaction = (OOAction)action;
				currentState = oaction.perform(currentState);
			}
		}
		return currentState;
	}
	
	/*@Test
	public void testAgent() {
    	Location vacuumloc = new Location(0, 0);
		int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 5, 0, 0, 0, 5 }
		};
		VacuumState currentState = new VacuumState(vacuumloc, worldSquares);
		VacuumSearchAgent agent = new VacuumSearchAgent(qs -> {
			//HeuristicFunction hf = new GlobalDirtAndDistanceHeuristic();
			HeuristicFunction hf = new GlobalDirtHeuristic();  

			//Search search = new SimulatedAnnealingSearch(hf, new LinearScheduler(10));
			//Search search = new SimulatedAnnealingSearch(hf, new Scheduler(20, 0.45, 10));
			Search search = new SimulatedAnnealingSearch(hf, new Scheduler());
			return search;
		});
		for (int i=0; i <=10; i++) {
			if (currentState.getGlobalDirtCount()==0) {
				break;
			}
			List<Action> actions = agent.searchFromState(currentState);
			currentState = executePlan(currentState, actions);
		}
		assertTrue("There should be no "
    			+ "dirt and there is some in this map \n"+
    			currentState, currentState.getGlobalDirtCount()==0);
	}*/
	
	
	@Test
	public void testAgent() {
    	Location vacuumloc = new Location(0, 0);
		int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		VacuumState currentState = new VacuumState(vacuumloc, worldSquares);
		VacuumSearchAgent agent = new VacuumSearchAgent(qs -> {
			HeuristicFunction hf = new GlobalDirtAndDistanceHeuristic();
			Search search = new SimulatedAnnealingSearch(hf, new Scheduler());
			return search;
		});
		for (int i=0; i <=10; i++) {
			if (currentState.getGlobalDirtCount()==0) {
				break;
			}
			List<Action> actions = agent.searchFromState(currentState);
			currentState = executePlan(currentState, actions);
		}
		assertTrue("There should be no "
    			+ "dirt and there is some in this map \n"+
    			currentState, currentState.getGlobalDirtCount()==0);
	}
}
