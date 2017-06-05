package plannable;

import java.util.HashSet;
import java.util.Set;

import plannable.action.Action;
import plannable.predicate.Predicate;

public class State {
	private Set<Predicate> predicates = new HashSet<Predicate>();

	public Set<Predicate> getPredicates() {
		return predicates;
	}	
	
	public void addPredicate(Predicate p) {
		this.predicates.add(p);
	}
	
	public void removePredicate(Predicate p) {
		this.predicates.remove(p);
	}
	
	public void update(Action a, Set<Predicate> groundTruth) {
		for (Predicate p : a.addList) {
			this.addPredicate(p);
		}
		
		for (Predicate p : a.deleteList) {
			if (!groundTruth.contains(p))
				this.removePredicate(p);
		}
	}
}
