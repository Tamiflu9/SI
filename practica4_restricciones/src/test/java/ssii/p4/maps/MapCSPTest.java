package ssii.p4.maps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.MinConflictsStrategy;
import aima.core.search.csp.SolutionStrategy;
import ssii.p4.StepCounter;

public class MapCSPTest {
	
	private CSP csp;
	private StepCounter stepCounter = new StepCounter();
	private SolutionStrategy solver;
	
	@Before
	public void setup() {
		csp = new MapCSP();
		stepCounter.reset();
	}
	
	@Test
	public void testMinConflictsStrategy() {
		solver = new MinConflictsStrategy(1000);
		solver.addCSPStateListener(stepCounter);
		System.out.println("Map Coloring (Minimum Conflicts)");
		Assignment solution = solver.solve(csp);
		assertThat(solution, is(notNullValue()));
		System.out.println(solution);
		System.out.println(stepCounter.getResults() + "\n");
		assertThat(stepCounter.getDomainChanges(), equalTo(0));
	}
	
	@Test
	public void testImprovedBacktrackingStrategy() {
		solver = new ImprovedBacktrackingStrategy(true, true, true, true);
		solver.addCSPStateListener(stepCounter);
		System.out.println("Map Coloring (Backtracking + MRV + DEG + AC3 + LCV)");
		Assignment solution = solver.solve(csp);
		assertThat(solution, is(notNullValue()));
		System.out.println(solution);
		System.out.println(stepCounter.getResults() + "\n");
		assertThat(stepCounter.getAssignmentChanges(), equalTo(7));
		assertThat(stepCounter.getDomainChanges(), equalTo(3));
	}
	
	@Test
	public void testBacktrackingStrategy() {
		solver = new BacktrackingStrategy();
		solver.addCSPStateListener(stepCounter);
		System.out.println("Map Coloring (Backtracking)");
		Assignment solution = solver.solve(csp);
		assertThat(solution, is(notNullValue()));
		System.out.println(solution);
		System.out.println(stepCounter.getResults() + "\n");
		assertThat(stepCounter.getAssignmentChanges(), equalTo(27));
		assertThat(stepCounter.getDomainChanges(), equalTo(0));
	}
	
}
