package strips;

import java.util.List;

public interface Planner {
	/**
	 * The method creates a list of actions by given plannable
	 * @param plannable The plannable we get in order to create the actions list
	 * @return List of actions
	 */
	List<ActionPlan> plan(Plannable plannable);
}
