package adapters;

import java.util.HashMap;

import model.data.Position;
import searchLib.Action;
import searchLib.BFS;
import searchLib.CommonSearchable;
import searchLib.Searcher;
import searchLib.Solution;
import searchLib.State;

public class SearchablePushAdapter extends CommonSearchable {
	//Data members
	private Searcher<Position> searcher;
	private SearchableMoveAdapter moveAdapter;
	private Position characterInitPosition;

	//Constructor
	public SearchablePushAdapter(Position startPos, Position endPos, char[][] board) {
		super(startPos, endPos, board);
		this.searcher = new BFS<Position>();
		this.moveAdapter = new SearchableMoveAdapter(new Position(0,0), new Position(0,0), board);
		this.characterInitPosition = deleteCharacterFromBoard();
	}

	/**
	 * The method deletes the character from the board and return it's position
	 * @return The position of the character before we deleted it from board
	 */
	private Position deleteCharacterFromBoard() {
		for (int i = 0 ; i < this.board.length ; i++) {
			for (int j = 0 ; j < this.board[0].length ; j++) {
				if (this.board[i][j] == 'A') {
					this.board[i][j] = ' ';
					return new Position(i, j);
				}
			}
		}
		return null;
	}
	
	/**
	 * The method gets an action and the current position of the character and returns the new position according to the action direction
	 * @param action The action that says where we want to move the character to
	 * @param currPos The current position of the character
	 * @return The new position of the character
	 */
	private Position getCharacterPositionAfterAction(Action action, Position currPos) {
		if(action!= null){
			String actionName = action.getName();
			int x = currPos.getP_x(), y = currPos.getP_y();
			if(actionName.equals("move up")){
				return new Position(x , y-1);
			}
			else if(actionName.equals("move down")){
				return new Position(x , y+1);
			}
			else if(actionName.equals("move left")){
				return new Position(x-1 , y);
			}
			else if(actionName.equals("move right")){
				return new Position(x+1 , y);
			}
		}
		return this.characterInitPosition;
	}
	
	/**
	 * The method checks if the position is a free space
	 * @param p The position we check
	 * @return If the position is a free space
	 */
	private boolean isFreeSpace(Position p) {
		return ((this.board[p.getP_x()][p.getP_y()] == ' ') || (this.board[p.getP_x()][p.getP_y()] == 'o'));
	}

	/**
	 * The method checks if the character can move next to the box
	 * @param currPos The current position of the box that the character needs to get to (after it will move the box)
	 * @param characterPos The current position of the character
	 * @param characterTarget The position that the character needs to get to in order to move the box
	 * @return The solution
	 */
	private Solution getCharacterShortestPathToBox(Position currPos, Position characterPos, Position characterTarget) {
		char[][] stateBoard;
		stateBoard = copyBoard(this.board);
		stateBoard[currPos.getP_x()][currPos.getP_y()] = '@';
		stateBoard[characterPos.getP_x()][characterPos.getP_y()] = 'A';

		this.moveAdapter.init(characterPos, characterTarget, stateBoard);//Initiate move adapter with the character current position and target
		((BFS<Position>) this.searcher).init();//Initiate the BFS

		return this.searcher.search(this.moveAdapter);//Return the shortest path to the box
	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleStates(State<Position> state) {
		HashMap<Action, State<Position>> states = new HashMap<>();
		Position currBoxPos = state.getState(), newBoxPos, characterTarget, characterPos = getCharacterPositionAfterAction(state.getAction(), currBoxPos);
		Solution solution;
		State<Position> newState;

		if (characterPos == null){
			return null;
		}

		//In order to push the box up, there has to be a free space below the box for the character and above the box for the new position of the box
		//In order to push the box down, there has to be a free space above the box for the character and below the box for the new position of the box
		if (isValidPosition(currBoxPos) && isValidPosition(currBoxPos)) {
			if (isFreeSpace(currBoxPos) && isFreeSpace(currBoxPos)){
				//Move up
				characterTarget = new Position(currBoxPos.getP_x(), currBoxPos.getP_y()+1);
				solution = getCharacterShortestPathToBox(currBoxPos, characterPos, characterTarget);
				if (solution != null || characterTarget.equals(characterPos)) {//If there is a path to the box or the character is already in that position
					newBoxPos = new Position(currBoxPos.getP_x(), currBoxPos.getP_y()-1);
					newState = new State<Position>(newBoxPos, state.getCost() + 1);
					newState.setCameFromActions(solution.getActionsForSolution());
					states.put(new Action("move up"), newState);
				}

				//Move down
				characterTarget = new Position(currBoxPos.getP_x() , currBoxPos.getP_y()-1);
				solution = getCharacterShortestPathToBox(currBoxPos, characterPos, characterTarget);
				if (solution != null || characterTarget.equals(characterPos)) {//If there is a path to the box or the character is already in that position
					newBoxPos = new Position(currBoxPos.getP_x(), currBoxPos.getP_y()+1);
					newState = new State<Position>(newBoxPos, state.getCost() + 1);
					newState.setCameFromActions(solution.getActionsForSolution());
					states.put(new Action("move down"), newState);
				}
			}
		}

		//In order to push the box left, there has to be a free space right to the box for the character and left to the box for the new position of the box
		//In order to push the box right, there has to be a free space left to the box for the character and right to the box for the new position of the box
		if(isValidPosition(currBoxPos) && isValidPosition(currBoxPos)){
			if(isFreeSpace(currBoxPos) && isFreeSpace(currBoxPos)){
				//Move left
				characterTarget = new Position(currBoxPos.getP_x()+1 , currBoxPos.getP_y());
				solution = getCharacterShortestPathToBox(currBoxPos, characterPos, characterTarget);
				if(solution!=null || characterTarget.equals(characterPos)){//If there is a path to the box or the character is already in that position
					newBoxPos = new Position(currBoxPos.getP_x()-1 , currBoxPos.getP_y());
					newState = new State<Position>(newBoxPos, state.getCost() + 1);
					newState.setCameFromActions(solution.getActionsForSolution());
					states.put(new Action("move left"), newState);
				}

				//Move right
				characterTarget = new Position(currBoxPos.getP_x()-1 , currBoxPos.getP_y());
				solution = getCharacterShortestPathToBox(currBoxPos, characterPos, characterTarget);
				if(solution!=null || characterTarget.equals(characterPos)){//If there is a path to the box or the character is already in that position
					newBoxPos = new Position(currBoxPos.getP_x()+1 , currBoxPos.getP_y());
					newState = new State<Position>(newBoxPos, state.getCost() + 1);
					newState.setCameFromActions(solution.getActionsForSolution());
					states.put(new Action("move right"), newState);
				}
			}
		}
		return states;
	}

}