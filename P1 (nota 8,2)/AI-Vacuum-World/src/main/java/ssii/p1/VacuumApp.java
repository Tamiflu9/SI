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
package ssii.p1;

import ssii.p1.actions.Location;
import ssii.p1.world.VacuumWorld;
import ssii.p1.agent.VacuumSearchAgent;
import ssii.p1.state.VacuumState;
import aima.core.search.uninformed.BreadthFirstSearch;

public class VacuumApp {
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
		env.addAgent(new VacuumSearchAgent(BreadthFirstSearch::new));
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