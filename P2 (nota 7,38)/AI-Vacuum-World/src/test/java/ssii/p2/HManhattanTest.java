package ssii.p2;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Search;
import aima.core.search.informed.AStarSearch;
import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
import ssii.p2.agent.GlobalDirtAndDistanceHeuristic;
import ssii.p2.agent.HeuristicaManhattan;

public class HManhattanTest {
	
	@Test
    public void testAgent() {
    	Location vacuumloc = new Location(0, 0);
		int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 5, 0, 0, 0, 0,  0, 0, 5, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0,  0, 0, 0, 0, 0 }
		};
		
		VacuumState initialState = new VacuumState(vacuumloc, worldSquares);
		VacuumSearchAgent agent = new VacuumSearchAgent(qs -> {
			HeuristicFunction hf = new HeuristicaManhattan();
			Search search = new AStarSearch(qs, hf);
			return search;
		});
		List<Action> actions = agent.searchFromState(initialState);
		VacuumState currentState = initialState;
		for (Action action:actions) {
			OOAction oaction = (OOAction)action;
			currentState = oaction.perform(currentState);
		}
		assertTrue("There should be no "
    			+ "dirt and there is some in this map \n"+
    			currentState + " after executing "+
    			actions, (currentState).getGlobalDirtCount()==0);
    }
 
}
