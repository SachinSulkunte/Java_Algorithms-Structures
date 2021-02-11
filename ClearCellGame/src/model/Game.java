package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. 
 */

public abstract class Game {

	private int rows, cols;

	protected BoardCell[][] board;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {

		rows = maxRows;
		cols = maxCols;

		board = new BoardCell[rows][cols];

		for (int r = 0; r < rows; r++) {

			for (int c = 0; c < cols; c++) {

				board[r][c] = BoardCell.EMPTY;
			}
		}
	}

	public int getMaxRows() {

		return rows;
	}

	public int getMaxCols() {

		return cols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {

		this.board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {

		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {

		for (int c = 0; c < cols; c++) {

			board[rowIndex][c] = cell;
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {

		for (int r = 0; r < rows; r++) {

			board[r][colIndex] = cell;
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {

		for (int r = 0; r < rows; r++) {

			for (int c = 0; c < cols; c++) {

				board[r][c] = cell;
			}
		}
	}

	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}
