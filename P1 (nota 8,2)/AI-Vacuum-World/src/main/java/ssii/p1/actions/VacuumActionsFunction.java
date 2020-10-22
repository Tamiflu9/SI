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
package ssii.p1.actions;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import ssii.p1.state.VacuumState;

public class VacuumActionsFunction implements ActionsFunction {
	public Set<Action> actions(Object state) {				
		VacuumState vs=(VacuumState)state;

		return Arrays.asList(
				new Left(), new Right(),
				new Up(), new Down(),
				new Suck(), new Noop())
				.stream()
				.filter(action -> action.valid(vs))
				// https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashSet.html
				// Hash table and linked list implementation of the Set interface, with **predictable iteration order**
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
}
