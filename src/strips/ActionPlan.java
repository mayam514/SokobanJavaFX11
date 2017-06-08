package strips;

public class ActionPlan extends Predicate {
	//Data members
	private Clause preconditions;
	private Clause effect;
	private Clause deleteEffects;
	
	//Constructor
	public ActionPlan(String type, String id, String value) {
		super(type, id, value);
	}

	//Get and set methods
	public Clause getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Clause preconditions) {
		this.preconditions = preconditions;
	}

	public Clause getEffect() {
		return effect;
	}

	public void setEffect(Clause effect) {
		this.effect = effect;
	}
	
	public Clause getDeleteEffects() {
		return deleteEffects;
	}


	public void setDeleteEffects(Clause deleteEffects) {
		this.deleteEffects = deleteEffects;
	}
}
