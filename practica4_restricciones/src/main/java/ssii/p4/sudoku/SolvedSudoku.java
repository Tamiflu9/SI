package ssii.p4.sudoku;

import java.util.stream.IntStream;


/**
 * This class represents the solution for a Sudoku puzzle 
 * */
public class SolvedSudoku {
	private Integer[][] sol;
	private Integer[] fila;
	
	public SolvedSudoku(Integer[][] sol) {
		this.sol = sol;
	}
	
	public SolvedSudoku(String puzzle) {
		Integer[][] sol = new Integer[SudokuCSP.SQUARES_PER_SIDE][SudokuCSP.SQUARES_PER_SIDE];
		Integer[] fila = new Integer[SudokuCSP.SQUARES_PER_SIDE];
		
		for (int row = 0; row < SudokuCSP.SQUARES_PER_SIDE; row++) {
			for (int col = 0; col < SudokuCSP.SQUARES_PER_SIDE; col++) {
				char c = puzzle.charAt(row*SudokuCSP.SQUARES_PER_SIDE + col);
				sol[row][col] = Integer.parseInt(c + "");
			} 
		}
		this.sol = sol;
	}

	public Integer[][] getSol() {
		return this.sol;
	}

	public boolean rowConstraint(Integer[][] sol, int row) {
	    boolean[] constraint = new boolean[SudokuCSP.SQUARES_PER_SIDE];
	    return IntStream.range(0,  SudokuCSP.SQUARES_PER_SIDE)
	      .allMatch(column -> checkConstraint(sol, row, constraint, column));
	}
	
	public boolean columnConstraint(Integer[][] sol, int column) {
	    boolean[] constraint = new boolean[SudokuCSP.SQUARES_PER_SIDE];
	    return IntStream.range(0, SudokuCSP.SQUARES_PER_SIDE)
	      .allMatch(row -> checkConstraint(sol, row, constraint, column));
	}
	
	boolean checkConstraint(Integer[][] board, int row, boolean[] constraint, int column) {
		if (board[row][column] != null) {
			if (!constraint[board[row][column] - 1]) {
	            constraint[board[row][column] - 1] = true;
	        } else {
	            return false;
	        }
		}
	    return true;
	}

}
