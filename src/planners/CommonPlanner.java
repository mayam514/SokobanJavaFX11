package planners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import plannable.State;
import plannable.action.Action;
import plannable.predicate.Predicate;

public abstract class CommonPlanner implements Planner {
	protected Random rand = new Random();
	
	protected boolean isSatisfied(Predicate p, State state) {
		return state.getPredicates().contains(p);
	}
	
	protected Action getSatisfyingAction(Predicate p, List<Action> actions, List<Object> objects) {
		for (Action action : actions) {
			for (Predicate addP : action.getAddList()) {
				if (p.getName().equals(addP.getName()) && p.getArgs().length == addP.getArgs().length) {
					Map<String, Object> assignment = findAssignment(p, addP, action, objects);
					if (assignment != null)	{
						Action newAction = action.instantiate(assignment);
						return newAction;
					}
				}
			}
		}
		return null;
	}
	
	protected Map<String, Object> findAssignment(Predicate p, Predicate genericP, Action action, List<Object> objects) {
		Map<String, Object> assignment = new HashMap<String, Object>();
		
		Object[] args1 = p.getArgs();
		Object[] args2 = genericP.getArgs();
		Map<String, List<Object>> illegalAssignments = action.getIllegalAssignments();
		
		// Find match between action's variables and the predicate's constants
		for (int i = 0; i < args2.length; i++) {
			if (illegalAssignments.get(args2[i]) != null) {
				if (illegalAssignments.get(args2[i]).contains(args1[i]))
					return null;
			}
			assignment.put((String)args2[i], args1[i]);
		}
		
		List<Object> unusedObjects = new ArrayList<Object>();
		unusedObjects.addAll(objects);
		unusedObjects.removeAll(assignment.values());		
		
		// Find all unassigned variables (from the action's arguments)
		for (Object variable : action.getArgs()) {	
			if (!assignment.containsKey(variable)) {
				// Remove all illegal assignments
				List<Object> legalObjects = new ArrayList<Object>();
				legalObjects.addAll(unusedObjects);
				if (illegalAssignments.get(variable) != null) {
					legalObjects.removeAll(illegalAssignments.get(variable));
				}
				
				// Choose a random legal object
				int idx = rand.nextInt(legalObjects.size());
				Object chosenObject = legalObjects.get(idx);
				unusedObjects.remove(chosenObject);
				
				assignment.put((String)variable, chosenObject);
			}
		}		
		
		return assignment;
	}
}
