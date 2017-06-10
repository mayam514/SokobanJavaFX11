package searchLib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BFS<T> extends CommonSearcher<T> {
	// Data members
	private PriorityQueue<State<T>> openList;
	//private HashSet<State<T>> closedList;
	private List<State<T>> closedList;

	// Constructor
	public BFS() {
		this.init();
	}

	@Override
	public Solution search(Searchable<T> s) {
		openList.add(s.getInitialState());//Add the first node to the openList
		while(!openList.isEmpty()){//While there are states we didn't evaluate yet
			State<T> n = openList.poll();
			this.evaluatedNodes++;
			closedList.add(n);
			if(n.equals(s.getGoalState())){//If we got to the goalState
				return backTrace(n);
			}
			HashMap<Action, State<T>> map = s.getAllPossibleStates(n); //Create a hashmap of all possible moves
			if(map != null){
				for (Action a : map.keySet()) {
					State<T> sunN = map.get(a);
					//sunN.setCameFrom(n);
					if(!this.closedList.contains(sunN)){
						if(!this.openList.contains(sunN)){
							sunN.setCameFrom(n);
							sunN.setAction(a);
							openList.add(sunN);	
						}
						else{
							for(State<T> state : openList) {
								if(state.equals(sunN)){
									if(sunN.getCost() < state.getCost()){//If the new path is better than the previous one
										openList.remove(state);
										sunN.setCameFrom(n);
										sunN.setAction(a);
										openList.add(sunN);
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}


	@Override
	public void init() {
		super.init();
		openList = new PriorityQueue<>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> s1,State<T> s2) {
				return (int) (s1.getCost() - s2.getCost());
			}
		});

		//closedList = new HashSet<>();//Create a closed set of all the states we evaluated
		closedList = new LinkedList<State<T>>();
	}

}
