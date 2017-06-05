package adapters;
import java.util.HashMap;
import java.util.HashSet;

import commons.Level;
import model.data.Position;
import searchLib.Action;
import searchLib.Searchable;
import searchLib.State;


public class SearchableMoveAdapter implements Searchable<Position>{
	//Data members
	private Level level;
	private Position playerTarget;

	//Constructor
	public SearchableMoveAdapter(Level level, Position playerTarget) {
		this.level = level;
		this.playerTarget = playerTarget;
	}

	@Override
	public State<Position> getInitialState() {
		State<Position> initialState = new State<Position>(level.get_characters().get(0).get_position());
		return initialState;
	}

	@Override
	public HashSet<State<Position>> getGoalState() {
		HashSet<State<Position>> set = new HashSet<State<Position>>();
		State<Position> goal = new State<Position>(playerTarget);
		set.add(goal);
		return set;
	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleMoves(State<Position> state) {
		Position currPos = state.getState();//The current position of the player
		HashMap<Action, State<Position>> returnMap = new HashMap<Action, State<Position>>();
		HashMap<String, Position> possibleMoves = level.getAllPossibleNeighbors(currPos);

		for(String direction : possibleMoves.keySet()){
			Action a = new Action(direction);
			State<Position> s = new State<Position>(possibleMoves.get(direction));
			returnMap.put(a, s);
		}
		return returnMap;
	}
}
