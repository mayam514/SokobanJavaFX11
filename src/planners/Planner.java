package planners;

import plannable.Plannable;

public interface Planner {
	Plan computePlan(Plannable plannable, HeuristicMethods h);
}
