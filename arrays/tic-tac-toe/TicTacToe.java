import java.util.*;
import java.io.*;

public class TicTacToe {
	public static final int X = 1, Oh = -1; // players
	public static final int EMPTY = 0; // empty cell

	private int[][] board;
	private int currentPlayer;
	private int n; // board size
	private int win;

	public TicTacToe(int n) {
		this.n = n;
		clearBoard();
	}

	public void clearBoard() {
		win = EMPTY;
		board = new int[n][n];
		currentPlayer = X;
	}

	public void place(int rowp1, int colp1) 
		throws IllegalArgumentException {
		int row = rowp1-1;
		int col = colp1-1;
		if(row>(n-1)||col>(n-1)) {
			throw new IllegalArgumentException("Row/col too big.");
		} else if(row<0||col<0){
			throw new IllegalArgumentException("Row/col too small.");
		} else {
			board[row][col] = currentPlayer;
		}

		if(currentPlayer==X) { 
			currentPlayer = Oh;
		} else {
			currentPlayer = X;
		}
	}

	public int isWin() {
		// Check rows and cols.
		// Then check two diags. 
		int[] rowsum = new int[n];
		int[] colsum = new int[n];
		int diagsumNE = 0;
		int diagsumSE = 0;

		// all in one O(n^2) op
		for(int i = 0; i < n; i++ ) {
			rowsum[i] = 0;
			colsum[i] = 0;
			for(int j = 0; j < n; j++ ) {
				rowsum[i] += board[i][j];
				colsum[i] += board[j][i];
			}
			diagsumNE += board[(n-1)-i][i];
			diagsumSE += board[i][i];
			if(rowsum[i]==n||colsum[i]==n||diagsumNE==n||diagsumSE==n) {
				return X;
			} else if(rowsum[i]==-n||colsum[i]==-n||diagsumNE==-n||diagsumSE==-n) {
				return Oh;
			}
		}
		
		return 0;
	}


	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("-----\n");
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[r].length; c++) { 
				if(board[r][c]==X){
					sb.append("X");
				} else if(board[r][c]==Oh) {
					sb.append("O");
				} else {
					sb.append(" ");
				}
			}	
			sb.append("\n");
		}
		sb.append("-----\n");
		int win = isWin();
		if(win>0){
			sb.append("Winner: X");
		} else if(win<0) {
			sb.append("Winner: O");
		}
		return sb.toString();
	}


	public static void main(String[] args) { 
		TicTacToe game = new TicTacToe(3);
		game.clearBoard();
		System.out.println(game.toString());
		game.place(1,1);
		System.out.println(game.toString());
		game.place(1,2);
		System.out.println(game.toString());
		game.place(3,1);
		System.out.println(game.toString());
		game.place(2,3);
		System.out.println(game.toString());
		game.place(2,1);
		System.out.println(game.toString());
	}
}
