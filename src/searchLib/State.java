package searchLib;

import java.util.List;

public class State<T> {
	//Data members
	private T state;
	private State<T> cameFrom;
	private Action action;
	private double cost;
	private List<Action> cameFromActions;
	
	//Constructor
	public State(T state, double cost) {
		this.state = state;
	}
	
	//Get and set methods
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public List<Action> getCameFromActions() {
		return cameFromActions;
	}

	public void setCameFromActions(List<Action> cameFromActions) {
		this.cameFromActions = cameFromActions;
	}
	
	@Override
	public String toString() {		
		return state.toString();
	}
	
	@Override
	public int hashCode() {		
		return state.hashCode();
	}
		
	@Override
	public boolean equals(Object o) {
		State<T> s = (State<T>)o;
		return state.equals(s.state);
	}

	

	
}
