package strips;

import java.util.Set;

public interface Plannable {
	/**
	 * The method returns the clause that symbolizes the goal
	 * @return The clause that symbolizes the goal
	 */
	Clause getGoal();
	
	/**
	 * The method returns the clause that symbolizes the knowledge base
	 * @return The clause that symbolizes the knowledge base
	 */
	Clause getKnowledgeBase();
	
	/**
	 * The method returns a set of actions that will satisfy the predicate
	 * @param top The predicate we need to satisfy
	 * @return set of actions that will satisfy the predicate
	 */
	Set<ActionPlan> getsatisfyingActions(Predicate top);
	
	/**
	 * The method returns an action that will satisfy the predicate
	 * @param top The predicate we want to satisfy
	 * @param knowledgeBase The knowledge base we have
	 * @return The action that will satisfy the predicate
	 */
	ActionPlan getsatisfyingAction(Predicate top, Clause knowledgeBase);
}
