package planners;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import plannable.Goal;
import plannable.Plannable;
import plannable.State;
import plannable.action.Action;
import plannable.predicate.Predicate;

public class Strips extends CommonPlanner {

	@Override
	public Plan computePlan(Plannable plannable, HeuristicMethods h) {
		Stack<Object> stack = new Stack<Object>();
		
		stack.push(plannable.getGoal());
		State currState = plannable.getInitialState();
		List<Action> actions = plannable.getAllActions();
		List<Object> objects = plannable.getAllObjects();
		List<Action> planActions = new ArrayList<Action>();
		
		while (!stack.isEmpty()) {
			Object obj = stack.pop();
						
			if (obj instanceof Goal) {
				handleComplexGoal(h, stack, obj);
			}
			else if (obj instanceof Predicate) {
				Predicate p = (Predicate)obj;
				if (!isSatisfied(p, currState)) {
					Action a = getSatisfyingAction(p, actions, objects);
					if(a != null){
						stack.push(a);
					}
					for (Predicate precond : a.getPreconditions()) {
						stack.push(precond);
					}
				}	
			}
			else if (obj instanceof Action) {
				Action a = (Action)obj;
				currState.update(a, plannable.groundTruth());
				planActions.add(a);		
			}
			else {
				throw new IllegalArgumentException("Invalid object in stack");
			}
		}
		
		return new Plan(planActions);
	}

	private void handleComplexGoal(HeuristicMethods h, Stack<Object> stack, Object obj) {
		Goal g = (Goal)obj;
		List<Predicate> predicates;
		if (h != null) 
			predicates = h.decomposeGoal(g);
		else
			predicates = g.getPredicates();
		for (Predicate p : predicates) {
			stack.push(p);
		}
	}
	
	

}
