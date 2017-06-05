package plannable.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plannable.predicate.Predicate;

public abstract class Action {
	//Data members
	protected String name;
	protected Object[] args;
	protected List<Predicate> preconditions = new ArrayList<Predicate>();//The predicates that we have in the beginning
	protected List<Predicate> addList = new ArrayList<Predicate>();//The predicates that we need to add in order to achieve the assignment
	protected List<Predicate> deleteList = new ArrayList<Predicate>();//The predicates that we need to delete from the preconditions in order to achieve the assignment(knowledgeBase)
	//protected Map<String, List<Object>> illegalAssignments = new HashMap<>();

	
	/*public Map<String, List<Object>> getIllegalAssignments() {
		return illegalAssignments;
	}
*/
	//Constructors
	public Action() {
		
	}

	public Action(String name, Object... args) {
		this.name = name;
		this.args = args;
	}

	//Get and set methods
	public Object[] getArgs() {
		return args;
	}

	public List<Predicate> getPreconditions() {
		return preconditions;
	}

	public List<Predicate> getAddList() {
		return addList;
	}

	public List<Predicate> getDeleteList() {
		return deleteList;
	}

	/**
	 * The method creates an action with the values of this action
	 * @param assignment The parameters of this action
	 * @return The action we create
	 */
	public Action instantiate(Map<String, Object> assignment) {
		Action newAction = null;
		switch(this.name){//Initiate the action by the name that we got in the c-tor
		case "Move":
		case "move":
		case "MOVE":
			newAction = new Move();
			break;
		case "Push":
		case "push":
		case "PUSH":
			//TODO
			//newAction = new Push(this.name);
			break;
		default:
			System.out.println("Action not found");
			break;
		}
		newAction.args = new Object[this.args.length];
		int count = 0;

		for (Object arg : this.args) {//Add values to the new action from this action
			newAction.args[count++] = assignment.get((String)arg);
		}

		newAction.preconditions = new ArrayList<>();
		for (Predicate p : this.preconditions) {
			newAction.preconditions.add(p.instantiate(assignment));
		}

		for (Predicate p : this.addList) {
			newAction.addList.add(p.instantiate(assignment));
		}

		for (Predicate p : this.deleteList) {
			newAction.deleteList.add(p.instantiate(assignment));
		}
		return newAction;
	}

	/**
	 * The method executes the action
	 */
	public abstract void execute();

	@Override
	public String toString() {
		String str = this.name + "(";
		for (Object arg: args) {
			str += arg.toString() + ",";
		}
		str += ")";
		return str;
	}
}