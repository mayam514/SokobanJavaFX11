package planners;

import java.util.List;

import plannable.Goal;
import strips.Predicate;

public interface HeuristicMethods {
	List<Predicate> decomposeGoal(Goal g);
}
