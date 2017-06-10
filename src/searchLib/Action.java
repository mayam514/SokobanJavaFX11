package searchLib;

import java.util.List;

public class Action {
	//Data members
	private String name;
	//private List<Action> history;

	//Constructor
	public Action(String name) {
		this.name = name;
	}

	//Get and set methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Action> getHistory() {
		return history;
	}
	public void setHistory(List<Action> history) {
		this.history = history;
	}*/

	@Override
	public boolean equals(Object obj) {
		Action a = (Action)obj;
		return a.name.equals(name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name;
	}
}