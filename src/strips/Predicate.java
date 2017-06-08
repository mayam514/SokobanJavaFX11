package strips;

public class Predicate {
	//Data members
	private String type;
	private String id;
	private String value;
	
	//Constructor
	public Predicate(String type, String id, String value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}
	
	//Get and set methods
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int hashCode(){
		return (type+id+value).hashCode();
	}
	
	@Override
	public String toString(){
		return type + "_" + id + "=" + value;
	}
	
	/**
	 * The method checks if the two predicates are equal
	 * @param p The predicate we check
	 * @return true if the predicates are equals
	 */
	public boolean equals(Predicate p){
		return (type.equals(p.type) && (id.equals(p.id)) && value.equals(p.value));
	}
	
	/**
	 * The method checks if the this predicate satisfies the given p predicate
	 * @param p The predicate we check if satisfied by this predicate
	 * @return true if the predicate is satisfied by this predicate
	 */
	public boolean isSatisfies(Predicate p){
		return (type.equals(p.type) && (id.equals(p.id) || p.id.equals("?")) && value.equals(p.value));
	}
	
	/**
	 * The method checks if there's a contradiction between this predicate and the other that we get
	 * @param p The predicate we get to check if contradicts to ours
	 * @return true if the predicates contradict
	 */
	public boolean isContradicts(Predicate p){
		return (type.equals(p.type) && id.equals(p.id) && !value.equals(p.value));
	}
	
	
}