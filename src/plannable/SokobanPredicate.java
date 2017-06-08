package plannable;

import strips.Predicate;

public class SokobanPredicate extends Predicate {

	public SokobanPredicate(String type, String id, String value) {
		super(type, id, value);
	}

	@Override
	public boolean isContradicts(Predicate p){
		return super.isContradicts(p) || (!getId().equals(p.getId()) && getValue().equals(p.getValue()));
	}
}
