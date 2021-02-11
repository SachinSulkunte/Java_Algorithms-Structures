package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {

	private int row, col, strategy, score;
	private Random rand;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {

		super(maxRows, maxCols);

		row = maxRows;
		col = maxCols;
		this.strategy = strategy;
		rand = random;

		score = 0;
	}

	@Override
	public boolean isGameOver() {

		int counter = 0;

		for (int c = 0; c < col; c++) {

			if (getBoardCell(board.length - 1, c) == BoardCell.EMPTY) {

				counter++; // checks if every cell is empty in that row
			}
		}

		if (counter == col) {

			return false;
		} else {

			return true;
		}
	}

	@Override
	public int getScore() {

		return score;
	}

	@Override
	public void nextAnimationStep() {

		if (isGameOver() == false) {

			shiftArray(true);
			for (int c = 0; c < col; c++) {

				setBoardCell(0, c, BoardCell.getNonEmptyRandomBoardCell(rand));
			}
		}
	}

	@Override
	public void processCell(int rowIndex, int colIndex) {

		int l = colIndex - 1; // different types of movement
		int r = colIndex + 1;
		int u = rowIndex - 1;
		int d = rowIndex + 1;

		boolean suspended = false;

		if (board[rowIndex][colIndex] != BoardCell.EMPTY) {

			while (l >= 0 && suspended == false) {

				if (board[rowIndex][l] == board[rowIndex][colIndex]) {

					setBoardCell(rowIndex, l, BoardCell.EMPTY);
					score++;
				} else
					suspended = true;

				l--; // checks further left of selected block
			}

			suspended = false; // resets

			while (r < col && suspended == false) {

				if (board[rowIndex][r] == board[rowIndex][colIndex]) {

					setBoardCell(rowIndex, r, BoardCell.EMPTY);
					score++;
				} else {
					suspended = true;
				}

				r++; // checks further right of selected cell
			}

			l = colIndex - 1; // reset values
			r = colIndex + 1;
			u = rowIndex - 1;
			d = rowIndex + 1;
			suspended = false;

			while (u >= 0 && suspended == false) {

				if (board[u][colIndex] == board[rowIndex][colIndex]) {

					setBoardCell(u, colIndex, BoardCell.EMPTY);
					score++;
				} else
					suspended = true;

				u--; // checks even higher positions
			}

			l = colIndex - 1; // reset values
			r = colIndex + 1;
			u = rowIndex - 1;
			d = rowIndex + 1;
			suspended = false;

			while (u >= 0 && suspended == false) {

				if (l >= 0) { // checks upper left diagonals

					if (board[u][l] == board[rowIndex][colIndex]) {

						setBoardCell(u, l, BoardCell.EMPTY);
						score++;
					} else
						suspended = true;
				}

				u--;
				l--;
			}

			l = colIndex - 1; // reset values
			r = colIndex + 1;
			u = rowIndex - 1;
			d = rowIndex + 1;
			suspended = false;

			while (u >= 0 && suspended == false) {

				if (r < col) {

					if (board[u][r] == board[rowIndex][colIndex]) {

						setBoardCell(u, r, BoardCell.EMPTY);
						score++;
					} else
						suspended = true;

				}

				u--; // checks even higher positions
				r++; // checks further diagonals to the right
			}

			l = colIndex - 1; // reset values
			r = colIndex + 1;
			u = rowIndex - 1;
			d = rowIndex + 1;
			suspended = false;

			while (d < row && suspended == false) {

				if (board[d][colIndex] == board[rowIndex][colIndex]) {

					setBoardCell(d, colIndex, BoardCell.EMPTY);
					score++;
				} else
					suspended = true;

				d++;
			}

			d = rowIndex + 1;
			suspended = false;

			while (d < row && suspended == false) {

				if (l >= 0) {

					if (board[d][l] == board[rowIndex][colIndex]) {

						setBoardCell(d, l, BoardCell.EMPTY);
						score++;
					} else
						suspended = true;
				}

				d++;
				l--;
			}

			d = rowIndex + 1;
			suspended = false;

			while (d < row && suspended == false) {

				if (r < col) {

					if (board[d][r] == board[rowIndex][colIndex]) {

						setBoardCell(d, r, BoardCell.EMPTY);
						score++;
					} else
						suspended = true;
				}

				d++; // checks lower rows
				r++; // checks lower diagonals on the right
			}

			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
		}

		shiftArray(false);
	}

	// Auxiliary Methods
	private BoardCell[][] shiftArray(boolean shiftDown) {

		BoardCell[][] copy = new BoardCell[row][col];

		for (int r = 0; r < row - 1; r++) {

			for (int c = 0; c < col; c++) {

				copy[r][c] = board[r][c];
			}
		}

		if (shiftDown == true) { // for shifting rows down when adding new one

			for (int i = 0; i < row - 1; i++) {

				for (int j = 0; j < col; j++) {

					board[i + 1][j] = copy[i][j]; // shifts value down one row
				}
			}
		}

		else if (shiftDown == false) { // for collapsing rows

			for (int r = 0; r < row - 1; r++) {

				if (isCleared(r) == true) {

					for (int i = r; i < row - 2; i++) {

						for (int c = 0; c < col; c++) {

							board[i][c] = copy[i + 1][c]; // shifts value up one row
							board[i + 1][c] = BoardCell.EMPTY;
						}
					}
				}
			}
		}

		return board;
	}

	private boolean isCleared(int r) {

		int counter = 0;

		for (int c = 0; c < board[0].length; c++) {

			if (board[r][c] == BoardCell.EMPTY) {

				counter++;
			}
		}

		if (counter == col) {

			return true;
		} else
			return false;
	}
}