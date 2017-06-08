package searchLib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstra<T> extends CommonSearcher<T>{
	//Data members
	private PriorityQueue<State<T>> openList;
	private HashSet<State<T>> closeList;

	//Constructor
	public Dijkstra() {
		this.evaluatedNodes = 0;
		openList = new PriorityQueue<>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> s1 ,State<T> s2) {
				return (int) (s1.getCost() - s2.getCost());
			}
		});
		
		closeList = new HashSet<>();
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
		openList.add(s.getInitialState());

		while(!openList.isEmpty()){
			State<T> n = openList.poll();
			this.evaluatedNodes++;
			closeList.add(n);
			HashMap<Action, State<T>> map = s.getAllPossibleStates(n); 
			for (Action a : map.keySet()) {
				State<T> sunN = map.get(a);
				sunN.setCameFrom(n);
				if(!closeList.contains(sunN)){
					if(!openList.contains(sunN))
						openList.add(sunN);	
					else{
						for (State<T> state : openList) {
							if(state.equals(sunN)){
								if(sunN.getCost() < state.getCost()){//found lower cost
									openList.remove(state);
									openList.add(sunN);
								}
							}
						}
					}
				}
			}
		}
		if(this.closeList.contains(s.getGoalState())){
			for (State<T> state : closeList) {
				if(state.equals(s.getGoalState()))
					return this.backTrace(state);
			} 
		}
		return null;
	}
}