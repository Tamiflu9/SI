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
package ssii.p1.world;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.EnvironmentState;
import aima.core.agent.Percept;
import aima.core.agent.impl.AbstractEnvironment;
import ssii.p1.actions.Location;
import ssii.p1.actions.OOAction;
import ssii.p1.state.VacuumState;
import ssii.p1.agent.ObservableVacuumPercept;


public class VacuumWorld extends AbstractEnvironment {

	/* usa VacuumState como su EnvironmentState*/
	private VacuumState currentState;

	public VacuumWorld(Location vacuumloc, int [][] world) {
		currentState = new VacuumState(vacuumloc, world);
	}

	@Override
	public EnvironmentState getCurrentState() {
		return currentState;
	}

	@Override
	public EnvironmentState executeAction(Agent agent, Action action) {
		if (action instanceof OOAction){
			OOAction oa=(OOAction) action;
			this.currentState=oa.perform(currentState);
		}
		return currentState;

	}

	/* define los perceptos que se le pasan a los agentes presentes en este entorno*/
	@Override
	public Percept getPerceptSeenBy(Agent anAgent) {
		return new ObservableVacuumPercept(currentState);
	}
		

}
