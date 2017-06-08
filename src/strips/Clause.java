package strips;

import java.util.HashSet;

public class Clause extends Predicate {
	//Data members
	private HashSet<Predicate> predicates;
	
	//Constructor
	public Clause(Predicate...predicates) {
		super("And", "", "");
		if(predicates != null) {
			this.predicates = new HashSet<>();
			for(Predicate p : predicates){
				this.predicates.add(p);
			}
			updateDescription();
		}
	}
	
	//Get and set methods
	public HashSet<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(HashSet<Predicate> predicates) {
		this.predicates = predicates;
	}

	/**
	 * The method updates the description of the Clause
	 */
	public void updateDescription() {
		setValue("{");
		for(Predicate p : predicates){
			setValue(getValue() + p.toString() + " & ");
		}
		setValue(getValue() + "}");
	}
	
	/**
	 * 
	 * @param effect
	 */
	public void update(Clause effect){
		//For each effect predicates (p), remove each knowledgebase predicate (pr) if p contradicts pr
		effect.predicates.forEach((Predicate p) -> predicates.removeIf((Predicate pr) ->  p.isContradicts(pr)));
		predicates.addAll(effect.predicates);
	}
	
	/**
	 * The method adds a predicate to the predicates hashSet
	 * @param p The predicate we want to add to the hashSet
	 */
	public void add(Predicate p){
		if(predicates == null){
			predicates = new HashSet<>();
		}
		this.predicates.add(p);
		updateDescription();
	}
	
	/**
	 * The method checks if all the predicates in the given clause can be satisfied by our clause(predicates set)
	 * @param c The clause that we check
	 * @return true if all the predicates in our set satisfies the clause
	 */
	public boolean isSatisfies(Clause c){
		for(Predicate p : c.predicates){
			if(!isSatisfies(p)){
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * The method checks if all the predicates in the given clause can be satisfied by our clause(predicates set)
	 * @param c The clause that we check
	 * @return true if all the predicates in our set contradict the clause
	 */
	public boolean isContradicts(Clause clause){
		for(Predicate p : clause.predicates){
			if(!isContradicts(p))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean isSatisfies(Predicate p){//The method checks if any of the predicates set satisfies p
		if(p instanceof Clause){
			Clause clause = (Clause)p;
			for(Predicate pr : clause.predicates){
				if(!isSatisfies(pr)){
					return false;
				}	
			}
			return true;
		}
		
		for(Predicate pr : predicates){
			if(pr.isSatisfies(p)){
				return true;
			}	
		}
		return false;
	}

	@Override
	public boolean isContradicts(Predicate p){
		for(Predicate pr : predicates)
			if(pr.isContradicts(p))
				return true;
		return false;
	}
	
}