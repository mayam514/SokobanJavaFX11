package blocksworld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import commons.Level;
import plannable.Goal;
import plannable.Plannable;
import plannable.State;
import plannable.action.Action;
import plannable.action.Move;
import plannable.predicate.Predicate;

public class BlocksWorldAdapter implements Plannable {
	Level level;

	public public BlocksWorldAdapter(Level level) {
		// TODO Auto-generated constructor stub
		this.level = level;
	}
	@Override
	public Goal getGoal() {
		List<Predicate> list = new ArrayList<Predicate>();

		//����� �� �� �������� ���� ����� ���� �� ����� �� ����� ��� �����
		list.add(new Predicate("On", "B", "A"));
		list.add(new Predicate("Clear", "B"));
		list.add(new Predicate("Clear", "C"));
		Goal goal = new Goal(list);
		return goal;
	}

	@Override
	public State getInitialState() {
		State state = new State();
		//������ �� ����� ������
		//������ �� ���� ������ ��� ���� ������
		for(Player player : level.get)
		state.addPredicate(new Predicate("On", "A", "B"));
		state.addPredicate(new Predicate("On", "B", "Table"));
		state.addPredicate(new Predicate("Clear", "A"));
		state.addPredicate(new Predicate("Clear", "C"));
		state.addPredicate(new Predicate("Clear", "Table"));

		return state;
	}

	@Override
	public List<Action> getAllActions() {
		//������ �� 2 �������
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Move());
		return actions;
	}

	@Override
	public List<Object> getAllObjects() {
		List<Object> objects = new ArrayList<>();
		//������ �� �� �������� ������� �����
		objects.add("A");
		objects.add("B");
		objects.add("C");
		objects.add("Table");
		return objects;
	}

	@Override
	public Set<Predicate> groundTruth() {
		//����� ����� ���� ����� ����� ����� ������
		HashSet<Predicate> groundTruth = new HashSet<Predicate>();
		groundTruth.add(new Predicate("Clear", "Table"));
		return groundTruth;
	}

}