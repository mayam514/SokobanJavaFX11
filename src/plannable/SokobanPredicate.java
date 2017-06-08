package plannable;

import strips.Predicate;

public class SokobanPredicate extends Predicate {

	public SokobanPredicate(String type, String id, String value) {
		super(type, id, value);
	}

	@Override
	public boolean contradicts(Predicate p){
		return super.contradicts(p) || (!getId().equals(p.getId()) && getValue().equals(p.getValue()));
	}
}
