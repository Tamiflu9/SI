package ssii.p4.sudoku;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.MinConflictsStrategy;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.SolutionStrategy;

public class SudokuCSPTest { 
	private static final String puzzle1 = "" + //
		            "53..7...." + //
		            "6..195..." + //
		            ".98....6." + //
		            "8...6...3" + //
		            "4..8.3..1" + //
		            "7...2...6" + //
		            ".6....28." + //
		            "...419..5" + //
		            "....8..79";
	
	private static final String easyPuzzle = "" + //
            "..3.2.6.." + //
            "9..3.5..1" + //
            "..18.64.." + //
            "..81.29.." + //
            "7.......8" + //
            "..67.82.." + //
            "..26.95.." + //
            "8..2.3..9" + //
            "..5.1.3..";
			
	private static final String obviousPuzzle = "" + //
            "........." + //
            "........." + //
            "........." + //
            "........." + //
            "........." + //
            "........." + //
            "........." + //
            "........." + //
            ".........";
	
	private static final String noSolutionsPuzzle = "" + //
            "..312.6.." + //
            "9..3.5..1" + //
            "..18.64.." + //
            "..81.29.." + //
            "7.......8" + //
            "..67.82.." + //
            "..26.95.." + //
            "8..2.3..9" + //
            "..5.1.3..";
	
	private static final String noSolutionsPuzzle2 = "..9.7...5..21..9..1...28....7...5..1..851.....5....3.......3..68........21.....87";
	
	private static final String harderPuzzle = "" + //
            "4173698.5" + //
            ".3......." + //
            "...7....." + //
            ".2.....6." + //
            "....8.4.." + //
            "....1...." + //
            "...6.3.7." + //
            "5..2....." + //
            "1.4......" ;
	
	private static final String hardestPuzzle = "" + //
            "1....7.9." + //
            ".3..2...8" + //
            "..96..5.." + //
            "..53..9.." + //
            ".1..8...2" + //
            "6....4..." + //
            "3......1." + //
            ".4......7" + //
            "..7...3.." ;
	
	private static class AssignedSudoku {
		private final SudokuCSP sudoku;
		private final Assignment assignment;
		
		public AssignedSudoku(SudokuCSP sudoku, Assignment assignment) {
			this.sudoku = sudoku;
			this.assignment = assignment;
		}
		
		/**
		 * @return whether or not the sudoku is solved as a CSP by
		 * the assignment
		 * */
		public boolean isCSPSolved() {
			return assignment != null && assignment.isSolution(sudoku);
		}
		
		/**
		 * If the sudoku CSP is solved by assignment, it applies the 
		 * assignment to each variable, thus making each variable
		 * domain have a size of 1
		 * */
		public void applyAssignmentIfCSPSolved() {
			if (isCSPSolved()) {
				sudoku.applyAssignment(assignment);
			}
		}
		
		/**
		 * Applies assertions to check that the sudoku is solved as
		 * defined https://en.wikipedia.org/wiki/Sudoku.
		 * */
		public void checkSudokuSolved() {
			assertThat(isCSPSolved(), is(true));
			sudoku.applyAssignment(assignment);	
			SolvedSudoku solvedSudoku = sudoku.toSolvedSudoku(assignment);
			SolvedSudokuTest.checkSudokuSolved(solvedSudoku);
		}
	}
	
	public void testParseSudoku() {
		System.out.println("testParseSudoku");
		SudokuCSP sudoku = new SudokuCSP(puzzle1);
		System.out.println(sudoku);
		System.out.println(sudoku.prettyPrint());
	}
	
	public AssignedSudoku solveSudoku(
			String puzzle, 
			SolutionStrategy solver) {
		SudokuCSP sudoku = new SudokuCSP(puzzle);
		System.out.println(sudoku.prettyPrint());
		sudoku.setupConstraints();
		
		Assignment solution = solver.solve(sudoku);
		AssignedSudoku assignedSudoku = new AssignedSudoku(sudoku, solution);
		assignedSudoku.applyAssignmentIfCSPSolved();
		System.out.println(sudoku.prettyPrint());
		System.out.println("Solution: " + solution + "\n");
		
		return assignedSudoku;
	}
	
	// TODO
	// Add tests for all the combinations of strategies and puzzles, following
	// the pattern of testObviousSudokuBacktrackingStrategy() below
	
	/*@Test
	public void testObviousSudokuBacktrackingStrategy() {
		System.out.println("testObviousSudokuBacktrackingStrategy");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(obviousPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}*/
	
	//puzzle1
	public void testMinConflictsStrategyPuzzle1() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(puzzle1, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	@Test
	public void testImprovedBacktrackingStrategyPuzzle1() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(puzzle1, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyPuzzle1() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(puzzle1, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	//easyPuzzle
	public void testMinConflictsStrategyEasyPuzzle() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(easyPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	@Test
	public void testImprovedBacktrackingStrategyEasyPuzzle() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(easyPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyEasyPuzzle() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(easyPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	//obviousPuzzle
	public void testMinConflictsStrategyObviousPuzzle() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(obviousPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	@Test
	public void testImprovedBacktrackingStrategyObviousPuzzle() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(obviousPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyObviousPuzzle() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(obviousPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	//noSolutionsPuzzle
	public void testMinConflictsStrategyNoSolutionsPuzzle() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testImprovedBacktrackingStrategyNoSolutionsPuzzle() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyNoSolutionsPuzzle() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	// noSolutionsPuzzle2
	public void testMinConflictsStrategyNoSolutionsPuzzle2() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle2, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testImprovedBacktrackingStrategyNoSolutionsPuzzle2() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle2, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyNoSolutionsPuzzle2() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(noSolutionsPuzzle2, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	//harderPuzzle
	public void testMinConflictsStrategyharderPuzzle() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(harderPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	@Test
	public void testImprovedBacktrackingStrategyharderPuzzle() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(harderPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyharderPuzzle() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(harderPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	// hardestPuzzle
	public void testMinConflictsStrategyhardestPuzzle() {
		System.out.println("testMinConflictsStrategyhardestPuzzle");
		SolutionStrategy solver = new MinConflictsStrategy(1000);
		AssignedSudoku solvedSudoku = solveSudoku(hardestPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	@Test
	public void testImprovedBacktrackingStrategyhardestPuzzle() {
		System.out.println("testImprovedBacktrackingStrategyhardestPuzzle");
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(true,false,true,false);
		AssignedSudoku solvedSudoku = solveSudoku(hardestPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	
	public void testBacktrackingStrategyhardestPuzzle() {
		System.out.println("testBacktrackingStrategyHardestPuzzle");
		SolutionStrategy solver = new BacktrackingStrategy();
		AssignedSudoku solvedSudoku = solveSudoku(hardestPuzzle, solver);
		solvedSudoku.checkSudokuSolved();
	}
	

 }
