package searchLib;

import commons.Level;
import model.data.Position;
import searchLib.Searchable;
import searchLib.State;

public abstract class CommonSearchable implements Searchable<Position>{
	//Data members
	private Position startPos;
	private Position endPos;
	protected char [][] board;
	protected Level level;

	//Constructors
	public CommonSearchable(Position startPos, Position endPos, Level level) {
		this.startPos = startPos;
		this.endPos = endPos;
		this.level = level;
		this.board = copyBoard(level.getLevelAsBoard());
		this.board[startPos.getP_x()][startPos.getP_y()] = ' ';
	}
	
	public CommonSearchable(Position startPos, Position endPos, char[][] board) {
		init(startPos, endPos, board);
	}
	
	/**
	 * The method initiates the commonSearchable data members
	 * @param startPos The starting position of the item
	 * @param endPos The final position of the item
	 * @param board The level board
	 */
	public void init(Position startPos, Position endPos, char[][] board){
		this.startPos = startPos;
		this.endPos = endPos;
		this.board = copyBoard(board);
		this.board[startPos.getP_x()][startPos.getP_y()] = ' ';
	}

	/**
	 * The method copies the level board
	 * @param board The board we want to copy
	 * @return The copied board
	 */
	protected char[][] copyBoard(char[][] board) {
		char[][] newBoard = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}

	/**
	 * The method checks if the position is not out of boundaries
	 * @param p The position we check
	 * @return If the position is not out of boundaries
	 */
	protected boolean isValidPosition(Position p) {
		if (p.getP_x() >= 0 && p.getP_x() < this.board.length && p.getP_y() >= 0 && p.getP_y() < this.board[0].length){
			return true;
		}
		return false;
	}
	
	@Override
	public State<Position> getInitialState() {
		State<Position> start=new State<Position>(startPos, 0);
		start.setCameFrom(null);
		start.setAction(null);
		return start;
	}

	@Override
	public State<Position> getGoalState() {
		return new State<Position>(endPos, 0);
	}
}