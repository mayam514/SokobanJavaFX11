package searchLib;

import java.util.HashMap;
import java.util.HashSet;

public interface Searchable<T> {
	State<T> getInitialState();
	HashSet<State<T>> getGoalState();
	HashMap<Action, State<T>> getAllPossibleMoves(State<T> state);
}
