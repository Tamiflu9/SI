/*Custom Vacuum World Example 
Copyright (C) 2015  Jorge J. Gomez-Sanz

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/	

package ssii.p2;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Search;
import aima.core.search.informed.AStarSearch;
import org.junit.Test;
import static org.junit.Assert.*;
import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
import ssii.p2.agent.GlobalDirtAndDistanceHeuristic;

public class AStarSearchHGlobalDirtAndDistanceTest {
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
			HeuristicFunction hf = new GlobalDirtAndDistanceHeuristic();
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
