package searchLib;

public class Action {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Action(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Action a = (Action)obj;
		return a.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
}
