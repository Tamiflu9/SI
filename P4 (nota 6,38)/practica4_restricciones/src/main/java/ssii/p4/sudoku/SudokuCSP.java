package ssii.p4.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.Variable;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Constraint;

import aima.core.search.csp.NotEqualConstraint;
/**
 * https://en.wikipedia.org/wiki/Sudoku
 * The objective is to fill a 9×9 grid with digits so that each column, each row, and each of 
 * the nine 3×3 subgrids that compose the grid (also called "boxes", "blocks", or "regions") 
 * contain all of the digits from 1 to 9
 * 
 * Cannot repeat numbers in the same row, column, or block
 * */
public class SudokuCSP  extends CSP {
	public static final Domain DOMAIN =
			new Domain(new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
	
	// boxes have 3x3 squares
	public static final int BOX_SIDE_SIZE = 3;
	// we have 3x3 boxes
	public static final int BOXES_PER_SIDE = 3;
	
	public static int SQUARES_PER_SIDE = BOXES_PER_SIDE * BOX_SIDE_SIZE;
	
	private Variable[][] vars;
	
	public SudokuCSP(String puzzle) {
		vars = new Variable[SQUARES_PER_SIDE][SQUARES_PER_SIDE];
		for (int row = 0; row < SQUARES_PER_SIDE; row++) {
			for (int col = 0; col < SQUARES_PER_SIDE; col++) {
				char c = puzzle.charAt(row*SQUARES_PER_SIDE + col);
				Variable var = new Variable(row + "_" + col);
				addVariable(var);
				vars[row][col] = var;
				if (c == '.') {
					setDomain(var, DOMAIN);
				} else {
					int x = Integer.parseInt(c + ""); 
					setDomain(var, new Domain(new Object[] {x}));
				}
			} 
		}
	}

	
	// FIXME: add the required constraints required to solved
	// this problem
	public void setupConstraints() {
		for (int row = 0; row < SQUARES_PER_SIDE; row ++){
            for (int col = 0; col < SQUARES_PER_SIDE; col ++){

                Variable curr = getVariables().get(row * SQUARES_PER_SIDE + col);
                for (int i = 0; i < SQUARES_PER_SIDE; i ++){
                    // row
                    if(i != col){
                        addConstraint(new NotEqualConstraint(curr, getVariables().get(row * SQUARES_PER_SIDE + i)));
                    }
                    // col
                    if(i != row){
                        addConstraint(new NotEqualConstraint(curr, getVariables().get(i * SQUARES_PER_SIDE + col)));
                    }
                }
                // block
                for (int m = 3 * (row / 3); m < 3 * (row / 3) + 3; m ++){
                    for (int n = 3 * (col / 3); n < 3 * (col / 3) + 3; n ++){
                        if(m != row || n != col) {
                            addConstraint(new NotEqualConstraint(curr, getVariables().get(m * SQUARES_PER_SIDE + n)));
                        }
                    }
                }
            }
        }
	
	}
	


	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Variable v : getVariables()) {
			s.append(v + " " + getDomain(v) + "\n" );	
		}
		return s.toString();
	}

	public String prettyPrint() {
		StringBuilder s = new StringBuilder();
		for (int row = 0; row < SQUARES_PER_SIDE; row++) {
			if (row > 0 && row % BOX_SIDE_SIZE == 0) {
				s.append("  ");
				for (int i = 0; i < SQUARES_PER_SIDE; i++) {
					s.append("--");	
				}
				s.append('\n');
			} 
			for (int col = 0; col < SQUARES_PER_SIDE; col++) {
				if (col % BOX_SIDE_SIZE == 0) {
					s.append('|');
				}
				Variable var = vars[row][col];
				Domain domain = getDomain(var);
				if (domain.size() == 1) {
					s.append(domain.get(0));
				} else {
					s.append('.');
				}
				if (col < SQUARES_PER_SIDE-1)  {
					s.append(' ');	
				}
				
			}
			s.append('|');
			s.append('\n');	
		}
		return s.toString();
	}
	
	public void applyAssignment(Assignment assignment) {
		for (Variable var: getVariables()) {
			if (assignment.hasAssignmentFor(var)) {
				Domain domain = new Domain(new Object[] {assignment.getAssignment(var)});
				setDomain(var, domain);
			}
		}
	}
	
	/**
	 * Assuming assignment is a solution of this CSP, it returns
	 * SolvedSudoku for the assigned values 
	 * */
	public SolvedSudoku toSolvedSudoku(Assignment assignment) {
		Integer[][] sol = new Integer[SQUARES_PER_SIDE][SQUARES_PER_SIDE];
		
		for (int row = 0; row < SQUARES_PER_SIDE; row++) {
			for (int col = 0; col < SQUARES_PER_SIDE; col++) {
				Variable v = vars[row][col];
				int x = (Integer) assignment.getAssignment(v);
				sol[row][col] = x;
			}
		}
		return new SolvedSudoku(sol);
	}
}
