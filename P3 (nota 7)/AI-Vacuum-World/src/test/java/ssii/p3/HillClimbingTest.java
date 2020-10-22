package ssii.p3;

import org.junit.Test;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Search;

import static org.junit.Assert.*;

import java.util.List;

import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
import ssii.p2.agent.GlobalDirtHeuristic;
import aima.core.search.local.HillClimbingSearch;


public class HillClimbingTest {
	@Test
	public void testAgent() {
    	Location vacuumloc = new Location(0, 0);
		int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 5 }
		};
		VacuumState initialState = new VacuumState(vacuumloc, worldSquares);
		VacuumSearchAgent agent = new VacuumSearchAgent(qs -> {
			HeuristicFunction hf = new GlobalDirtHeuristic();
			Search search = new HillClimbingSearch(hf);
			return search;
		});
		List<Action> actions = agent.searchFromState(initialState);
		VacuumState currentState = initialState;
		for (Action action:actions) {
			if (!action.isNoOp()) {
				System.out.println("es correcto, no queda basura");
				OOAction oaction = (OOAction)action;
				currentState = oaction.perform(currentState);	
			}
		}
	}
}
