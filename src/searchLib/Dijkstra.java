package searchLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstra<T> extends CommonSearcher<T>{
	// Data members
	private PriorityQueue<State<T>> openList;

	// Constructor
	public Dijkstra() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}

	/**
	 * The method gets a state from the openList
	 * @return the state we popped
	 */
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	@Override
	public Solution search(Searchable<T> s) {
		openList.add(s.getInitialState());//Add the first node to the openList
		HashSet<State<T>> closedSet = new HashSet<State<T>>();//Create a closed set of all the states we evaluated
		while (openList.size() > 0) {//While there are states we didn't evaluate yet
			State<T> n = popOpenList();
			closedSet.add(n);
			if (n.equals(s.getGoalState())) {//If we got to the goalState
				return backTrace(n);
			}
			HashMap<Action, State<T>> map = s.getAllPossibleMoves(n);//Create a hashmap of all possible moves
			ArrayList<State<T>> successors = new ArrayList<>(map.values());//Create a linkedlist of all possible states from the hashmap
			for (State<T> state : successors) {//For each possible state
				if (!closedSet.contains(state) && !openList.contains(state)) {//If the state isn't in the openlist and isn't the closedlist, we haven't discovered it yet and we need to update it's parent and add it to the openList
				state.setCameFrom(n);
					openList.add(state);
				} else {
					if(state.getCost() < n.getCost()){//If the new path is better than the previous one
						if(!openList.contains(state)){
							openList.add(state);
						}
						else{
							openList.remove(n);
							openList.add(state);
						}
					}
				}
			}
		}
		return null;
	}
}
