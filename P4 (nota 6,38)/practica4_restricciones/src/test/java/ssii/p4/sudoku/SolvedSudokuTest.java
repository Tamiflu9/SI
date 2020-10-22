package ssii.p4.sudoku;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ssii.p4.sudoku.SolvedSudoku;

public class SolvedSudokuTest {
	
	private static final String PUZZLE_OK_1 = "" + //
            "162857493" + //
            "534129678" + 
            "789643521" +
            "475312986" +
            "913586742" +
            "628794135" +
            "356478219" +
            "241935867" +
            "897261354";
	
	private static final String PUZZLE_OK_2 = "" + //
			"417369825" +
			"632158947" +
			"958724316" +
			"825437169" +
			"791586432" +
			"346912758" +
			"289643571" +
			"573291684" +
			"164875293";

	private static final String PUZZLE_OK_3 = "" + //
			"147258369" +
			"283769514" +
			"569134287" +
			"372485196" +
			"814926735" +
			"956317842" +
			"728641953" +
			"635892471" +
			"491573628";
			
	private static final String PUZZLE_NOT_OK_1 = "" + //
            "132857496" + //
            "534129678" + 
            "789643521" +
            "475312986" +
            "913586742" +
            "628794135" +
            "356478219" +
            "241935867" +
            "897261354";
	
	private static final String PUZZLE_NOT_OK_2 = "" + //
			"417369825" +
			"632158947" +
			"958724316" +
			"825437169" +
			"791586432" +
			"396412758" +
			"289643571" +
			"573291684" +
			"164875293";

	private static final String PUZZLE_NOT_OK_3 = "" + //
			"147258369" +
			"283769514" +
			"569134287" +
			"372485196" +
			"814926735" +
			"956317842" +
			"768241953" +
			"635892471" +
			"491573628";

	public static void checkSudokuSolved(SolvedSudoku sudoku) {
		final List<Integer> domain = Arrays.asList(1,2,3,4,5,6,7,8,9);
		assertThat(Arrays.asList(9,8,7,6,5,4,3,2,1), 
				containsInAnyOrder(domain.toArray()));
		
		// FIXME: replace by assertions that check that whether
		// sudoku is a correct solved sudoku or not
		//assertThat(true, is(true));
		
		// filas
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),0)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),1)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),2)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),3)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),4)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),5)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),6)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),7)));
		assertThat(true, is(sudoku.rowConstraint(sudoku.getSol(),8)));
		// columnas
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),0)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),1)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),2)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),3)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),4)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),5)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),6)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),7)));
		assertThat(true, is(sudoku.columnConstraint(sudoku.getSol(),8)));
	}
	
	@Test
	public void testPuzzleSolvedOk1() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_OK_1);
		checkSudokuSolved(sudoku);
	}
	
	@Test
	public void testPuzzleSolvedOk2() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_OK_2);
		checkSudokuSolved(sudoku);
	}
	
	@Test
	public void testPuzzleSolvedOk3() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_OK_3);
		checkSudokuSolved(sudoku);
	}
	
	@Test(expected=AssertionError.class)
	public void testPuzzleNotSolvedOk1() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_NOT_OK_1);
		checkSudokuSolved(sudoku);
	}
	
	@Test(expected=AssertionError.class)
	public void testPuzzleNotSolvedOk2() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_NOT_OK_2);
		checkSudokuSolved(sudoku);
	}
	
	@Test(expected=AssertionError.class)
	public void testPuzzleNotSolvedOk3() {
		SolvedSudoku sudoku = new SolvedSudoku(PUZZLE_NOT_OK_3);
		checkSudokuSolved(sudoku);
	}
	
}
