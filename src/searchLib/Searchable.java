package searchLib;

import java.util.HashMap;

public interface Searchable<T> {
	State<T> getInitialState();
	State<T> getGoalState();
	HashMap<Action, State<T>> getAllPossibleStates(State<T> state);
}
