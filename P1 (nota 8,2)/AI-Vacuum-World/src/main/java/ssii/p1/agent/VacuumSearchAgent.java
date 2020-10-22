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
package ssii.p1.agent;

import java.util.List;

import ssii.p1.actions.StochasticVacuumActionsFunction;
import ssii.p1.actions.VacuumActionsFunction;
import ssii.p1.actions.VacuumResultFunction;
import ssii.p1.state.VacuumState;
import aima.core.agent.Action;
import aima.core.agent.Percept;
import aima.core.agent.State;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.SimpleProblemSolvingAgent;
import aima.core.search.framework.TreeSearch;
import java.util.function.Function;
import aima.core.search.framework.Search;

/* Este agente mantiene una variable miembro de tipo VacuumState que corresponde a su representación del entorno, y
que actualiza de acuerdo a los perceptos que obtiene del entorno.*/
public class VacuumSearchAgent extends SimpleProblemSolvingAgent {
	private VacuumState ms;  //variable miembro 
	private Function<QueueSearch, Search> searchStrategy;
	private ActionsFunction actionsFunction;

	public VacuumSearchAgent(Function<QueueSearch, Search> searchStrategy,
			ActionsFunction actionsFunction) {
		this.searchStrategy = searchStrategy;
		this.actionsFunction = actionsFunction;
	}
	
	public VacuumSearchAgent(Function<QueueSearch, Search> searchStrategy) {
		this(searchStrategy, new VacuumActionsFunction());
	}
	
	

	public VacuumSearchAgent(Object searchStrategy2, StochasticVacuumActionsFunction actionsFunction2) {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected State updateState(Percept p) {
		ObservableVacuumPercept vp=(ObservableVacuumPercept)p;
		this.ms=vp.getState();
		return ms;
	}

	@Override
	protected Object formulateGoal() {
		// This is irrelevant because it is ignored on formulateProblem()
		// below. This is because the goal is always the same: cleaning all
		// the dirt
		return "SearchDirt";
	}

	/* metodo formulateProblem para construir un problema para calcular la secuencia de acciones para llegar a un estado solución
	desde el estado actual del agente, proporcionando funciones para calcular todas las
	acciones posibles desde un estado, y el resultado de aplicar una acción desde un estado
	dado.*/
	@Override
	protected Problem formulateProblem(Object goal) {	 		 
		ResultFunction resultFunction = new VacuumResultFunction();

		GoalTest goalTest = new VacuumGoalTest();		

		// Como se ve en el javadoc para este constructor, este usa
		// La función de costo de paso predeterminada (es decir, 1 por paso).
		Problem vacuumProblem=new Problem(
				ms,
				actionsFunction,
				resultFunction,
				goalTest);

		return vacuumProblem;
	}
	
	private void printSolution(List<Action> solution) {
		for (Action action: solution) {
			System.out.println("\t" + action);
		}
	}

	/* Un método protected List<Action> search(Problem problem) que calcula la lista
	de acciones solución para el problema dado. Para ello se utilizan clases que implementan
	estrategias de búsqueda como aima.core.search.uninformed.BreadthFirstSearch,
	proporcionada en la constructora de VacuumSearchAgent.*/
	@Override
	protected List<Action> search(Problem problem) {
		System.out.println("Searching for a solution");
		
		QueueSearch qs=new TreeSearch();
		Search search = searchStrategy.apply(qs);
		List<Action> actions;
		try {
			actions = search.search(problem);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Solution found:");
		printSolution(actions);
		
		return actions;
	}

	@Override
	protected void notifyViewOfMetrics() {}
	
	public List<Action> searchFromState(VacuumState initialState) {
		this.ms = initialState;
		Problem problem = formulateProblem(formulateGoal());
		return search(problem);
	}
}
