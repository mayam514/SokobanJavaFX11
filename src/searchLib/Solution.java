package searchLib;

import java.util.LinkedList;
import java.util.List;

public class Solution {
	//Data members
	private LinkedList<Action> actionsForSolution = new LinkedList<Action>();

	//Get and set methods
	public List<Action> getActionsForSolution() {
		return actionsForSolution;
	}

	public void setActionsForSolution(LinkedList<Action> actions) {
		this.actionsForSolution = actions;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Action a : actionsForSolution) {
			sb.append(a.getName()).append("\n");
		}
		
		return sb.toString();
	}
}
