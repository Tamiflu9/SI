package ssii.p1.actions;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import ssii.p1.state.VacuumState;

public class StochasticVacuumActionsFunction implements ActionsFunction{
	public Set<Action> actions(Object state) {				
		VacuumState vs=(VacuumState)state;

		return Arrays.asList(
				new Left(), new Right(),
				new Up(), new Down(),
				new Suck(), new Noop())
				.stream()
				.filter(action -> action.valid(vs))
				// https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
				// This class implements the Set interface, backed by a hash table (actually a HashMap instance). 
				// It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee 
				// that the order will remain constant over time. 
				.collect(Collectors.toCollection(HashSet::new));
				// .collect(Collectors.toSet());
	}
}
