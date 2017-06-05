package planners;

import java.util.List;

import plannable.Goal;
import plannable.predicate.Predicate;

public interface HeuristicMethods {
	List<Predicate> decomposeGoal(Goal g);
}
