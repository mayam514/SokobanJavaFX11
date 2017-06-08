package adapters;
import java.util.HashMap;

import model.data.Position;
import searchLib.Action;
import searchLib.CommonSearchable;
import searchLib.State;

public class SearchableMoveAdapter extends CommonSearchable{
	//Constructor
	public SearchableMoveAdapter(Position startPos, Position endPos, char[][] board) {
		super(startPos, endPos, board);
	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleStates(State<Position> state) {

		HashMap<Action, State<Position>> states = new HashMap<Action, State<Position>>();
		Position currPos = state.getState();//The current position of the player

		Position newPos = null;
		State<Position> newState = null;
		int x = currPos.getP_x(), y = currPos.getP_y();

		//Move up
		if (isValidPosition(new Position(x,y-1)) && (this.board[x][y-1] == ' ' || this.board[x][y-1] == 'o')) {
			newPos = new Position(x , y-1);
			newState = new State<Position>(newPos, state.getCost() + 1);
			states.put(new Action("move up"), newState);
		}
		
		//Move down
		if (isValidPosition(new Position(x,y+1)) && (this.board[x][y+1] == ' ' || this.board[x][y+1] == 'o')) {
			newPos = new Position(x , y+1);
			newState = new State<Position>(newPos, state.getCost() + 1);
			states.put(new Action("move down"), newState);
		}
		
		//Move left
		if (isValidPosition(new Position(x-1,y)) && (this.board[x-1][y] == ' ' || this.board[x-1][y] == 'o')) {
			newPos = new Position(x-1 , y);
			newState = new State<Position>(newPos, state.getCost() + 1);
			states.put(new Action("move down"), newState);
		}
		
		//Move right
		if (isValidPosition(new Position(x+1,y)) && (this.board[x+1][y] == ' ' || this.board[x+1][y] == 'o')) {
			newPos = new Position(x+1 , y);
			newState = new State<Position>(newPos, state.getCost() + 1);
			states.put(new Action("move down"), newState);
		}
		return states;
	}
}