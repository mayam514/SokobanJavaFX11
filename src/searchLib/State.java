package searchLib;

public class State<T> {
	private T state;
	private State<T> cameFrom;
	private Action action;
	private double cost;
	
	public State(T state) {
		this.state = state;
	}
	
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
