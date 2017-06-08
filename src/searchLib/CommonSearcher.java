package searchLib;

import java.util.LinkedList;

public abstract class CommonSearcher<T> implements Searcher<T> {
	//Data members
	protected int evaluatedNodes;
	
	/**
	 * The method back traces through the parents
	 * @param goalState the goal state
	 * @return the shortest path
	 */
	protected Solution backTrace(State<T> goalState) {
		LinkedList<Action> actions = new LinkedList<Action>();
		
		State<T> currState = goalState;
		while (currState.getCameFrom() != null) {
			actions.addFirst(currState.getAction());
			if((currState.getAction().getHistory()!=null)&&(currState.getAction().getHistory().size()!=0))
				for (int i = currState.getAction().getHistory().size()-1 ; i >= 0 ; i--) {
					actions.addFirst(currState.getAction().getHistory().get(i));
				}
			currState = currState.getCameFrom();
		}
		
		Solution sol = new Solution();
		sol.setActionsForSolution(actions);
		return sol;
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	
	@Override
	public void init() {
		this.evaluatedNodes = 0;
	}
}
