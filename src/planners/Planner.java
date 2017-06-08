package planners;

import strips.Plannable;

public interface Planner {
	Plan computePlan(Plannable plannable, HeuristicMethods h);
}
