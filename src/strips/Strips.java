package strips;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Strips implements Planner{
	//Data member
	private Plannable plannable;

	/*
	 * (non-Javadoc)
	 * @see strips.Planner#plan(strips.Plannable)
		Push goal into the stack
		Repeat until stack is empty
		If top is a multipart goal, push unsatisfied sub-goals into the stack V
		If top is a single unsatisfied goal, V
		Replace it with an action that satisfies the goal V
		Push the action preconditions into the stack V
		If top is an action, V
		Pop it from the stack V
		Simulate its execution and update the knowledge base with its effects V
		Add the action to the plan V
		If top is a satisfied goal, pop it from the stack V
	 */

	@Override
	public List<ActionPlan> plan(Plannable plannable) {
		LinkedList<ActionPlan> plan = new LinkedList<>();
		this.plannable = plannable;
		Stack<Predicate> stack = new Stack<>();
		stack.push(plannable.getGoal());
		while(!stack.isEmpty()){//While there are predicates that needs to be satisfied
			Predicate top = stack.peek();
			if(!(top instanceof ActionPlan)){//Not an action
				if(!plannable.getKnowledgeBase().isSatisfies(top)){//Not satisfied
					if(top instanceof Clause){//Unsatisfied set of predicates (multipart)
						Clause c = (Clause)top;
						stack.pop();
						for(Predicate p : c.getPredicates()){
							stack.push(p);
						}
					}
					else{//Unsatisfied single predicate 
						stack.pop();
						ActionPlan action = plannable.getsatisfyingAction(top, plannable.getKnowledgeBase());
						if(action != null){
							stack.push(action);
							stack.push(action.getPreconditions());
						}
					}
				}
				else{//Satisfied clause or predicate 
					stack.pop();
				}
			}
			else {//Action
				stack.pop();
				ActionPlan a = (ActionPlan)top;
				plannable.getKnowledgeBase().update(a.getEffect());
				for(Predicate delete : a.getDeleteEffects().getPredicates()){
					for(Predicate p : plannable.getKnowledgeBase().getPredicates()){
						if(delete.equals(p)){
							plannable.getKnowledgeBase().getPredicates().remove(p);
							plannable.getKnowledgeBase().updateDescription();
							break;
						}
					}
				}
				plan.add(a);//Add the action to the solution plan
			}
		}
		return plan;//The solution plan that satisfies the goal
	}
}