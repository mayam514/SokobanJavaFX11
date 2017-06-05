package plannable.predicate;

import commons.Level;

public class AllBoxesOnFinalPositionsPredicate extends Predicate {
	//Data member
	private Level level;
	
	//Constructor
	public AllBoxesOnFinalPositionsPredicate(String name, Object[] args, Level level) {
		super(name, args);
		this.level = level;
	}

	@Override
	public boolean isSatisfied() {
		if(level._isWinner()){
			return true;
		}
		return false;
	}
}
