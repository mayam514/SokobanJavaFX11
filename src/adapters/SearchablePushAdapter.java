package adapters;

import java.util.HashMap;
import java.util.HashSet;

import commons.Level;
import model.data.Position;
import searchLib.Action;
import searchLib.Searchable;
import searchLib.State;

public class SearchablePushAdapter implements Searchable<PlayerAndBox> {
	//Data members
	private Level level;
	private PlayerAndBox playerBoxPosition;
	private Position boxTarget;

	//Constructor
	public SearchablePushAdapter(Level level,PlayerAndBox position, Position boxTarget) {
		this.level = level;
		this.playerBoxPosition = position;
		this.boxTarget = boxTarget;
	}

	@Override
	public State<PlayerAndBox> getInitialState() {
		State<PlayerAndBox> initialState = new State<PlayerAndBox>(playerBoxPosition);
		return initialState;
	}

	@Override
	public HashSet<State<PlayerAndBox>> getGoalState() {
		HashSet<State<PlayerAndBox>> set = new HashSet<State<PlayerAndBox>>();
		HashMap<String, Position> playerPossibleMovesDirections = level.getAllPossibleNeighbors(boxTarget);
		for(Position possiblePlayerPossition : playerPossibleMovesDirections.values()){
			State<PlayerAndBox> goal = new State<PlayerAndBox>(new PlayerAndBox(boxTarget, possiblePlayerPossition));
			set.add(goal);
		}
		return set;
	}

	@Override
	public HashMap<Action, State<PlayerAndBox>> getAllPossibleMoves(State<PlayerAndBox> state) {
		PlayerAndBox currPos = state.getState();//The current position of the player and the box
		HashMap<Action, State<PlayerAndBox>> returnMap = new HashMap<Action, State<PlayerAndBox>>();
		HashMap<String, Position> boxPossiblePushDirections = level.getAllPossibleNeighbors(currPos.getPosBox());

		for(String direction : boxPossiblePushDirections.keySet()){
			Action a = new Action(direction);
			PlayerAndBox p = new PlayerAndBox(boxPossiblePushDirections.get(direction), currPos.getPosBox());//The new position of the player will be the old box position
			State<PlayerAndBox> s = new State<PlayerAndBox>(p);
			returnMap.put(a, s);
		}
		return returnMap;
	}
}