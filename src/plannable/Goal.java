package plannable;

import java.util.ArrayList;
import java.util.List;

import plannable.predicate.Predicate;

public class Goal {
	private List<Predicate> predicates = new ArrayList<Predicate>();

	public List<Predicate> getPredicates() {
		return predicates;
	}
	
	public Goal(List<Predicate> predicates) {
		this.predicates = predicates;
	}
}
