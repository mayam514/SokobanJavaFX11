package planners;

import java.util.ArrayList;
import java.util.List;

import plannable.action.Action;

public class Plan {
	private List<Action> actions = new ArrayList<Action>();
	
	public List<Action> getActions() {
		return actions;
	}
	
	public Plan(List<Action> actions) {
		this.actions = actions;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Action a : actions) {
			str += a.toString() + "\n";
		}
		return str;
	}
}
