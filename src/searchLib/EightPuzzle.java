package searchLib;

public class EightPuzzle {
	public static final int SIZE = 3;	
	
	private int[][] board = new int[SIZE][SIZE];
			
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				str += board[i][j];
			}
			str += "\n";
		}
		return str;		
	}
	
	public Position getEmptyCell(int[][] board) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(board[i][j] == 0) {
					return new Position(i, j);
				}
			}			
		}
		return null;
	}
}
