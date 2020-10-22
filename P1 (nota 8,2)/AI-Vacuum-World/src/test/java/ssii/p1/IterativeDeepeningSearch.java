package ssii.p1;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.uninformed.*;
import org.junit.Test;
import static org.junit.Assert.*;
import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.actions.StochasticVacuumActionsFunction;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;

public class IterativeDeepeningSearch {
	@Test
    public void testAgent() {
    	Location vacuumloc = new Location(0, 0);
    	int [][] worldSquares = new int[][] { 
			new int[] { 0, 0, 0, 5, 0, 0, 0, 0, 0, 0 }, 
			new int[] { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }	
		};
		
		VacuumState initialState = new VacuumState(vacuumloc, worldSquares);
		VacuumSearchAgent agent = new VacuumSearchAgent(qs-> new  aima.core.search.uninformed.IterativeDeepeningSearch());
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
