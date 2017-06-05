package plannable;

import java.util.List;
import java.util.Set;

import plannable.action.Action;
import plannable.predicate.Predicate;

public interface Plannable {
	Goal getGoal();
	State getInitialState();
	Set<Predicate> groundTruth();
	List<Action> getAllActions();
	List<Object> getAllObjects();
}
