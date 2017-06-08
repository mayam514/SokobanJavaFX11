package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import commons.Level;
import model.data.Box;
import model.data.Character;
import model.data.FinalPosition;
import plannable.Goal;
import plannable.State;
import plannable.action.Action;
import searchLib.Solution;
import strips.Plannable;
import strips.Predicate;

public class PlannableLevelAdapter implements Plannable{

	private Level level;
private Solution sol;//bigsol

	public PlannableLevelAdapter() {
		this.level = level;
	}

	@Override
	public Goal getGoal() {
		List<Predicate> list = new ArrayList<Predicate>();

		//לעבור על כל הקופסאות בשלב ולחשב עבור כל קופסה מה המטרה הכי קרובה
		List<FinalPosition> finalPositions = this.level.get_finalPositions();
		HashMap<Box,FinalPosition> boxAndFinalPosition = new HashMap<Box,FinalPosition>();
		int min = (int)Double.POSITIVE_INFINITY;

		for(Box box : level.get_boxes()){
			for(FinalPosition finalPosition : this.level.get_finalPositions()){
				int subX = Math.abs(box.get_position().getP_x() - finalPosition.get_position().getP_x());
				int subY = Math.abs(box.get_position().getP_y() - finalPosition.get_position().getP_y());
				if(subX + subY < min){
					min = subX + subY;
					boxAndFinalPosition.put(box,finalPosition);
				}
			}

		}

		list.add(new Predicate("On", "B", "A"));
		list.add(new Predicate("Clear", "B"));
		list.add(new Predicate("Clear", "C"));
		Goal goal = new Goal(list);
		return goal;
	}

	@Override
	public State getInitialState() {
		State state = new State();

		for(Character character : this.level.get_characters()){
			state.addPredicate(new Predicate("On", character, character.get_position()));
		}

		for(Box box : this.level.get_boxes()){
			state.addPredicate(new Predicate("On", box, box.get_position()));
		}

		for(FinalPosition finalPosition : this.level.get_finalPositions()){
			state.addPredicate(new Predicate("On", finalPosition, finalPosition.get_position()));
		}
		/*
		state.addPredicate(new Predicate("Clear", "A"));
		state.addPredicate(new Predicate("Clear", "C"));
		state.addPredicate(new Predicate("Clear", "Table"));
*/
		return state;
	}

	@Override
	public List<Action> getAllActions() {
		//להכניס את 2 האקטנים
		List<Action> actions = new ArrayList<Action>();
		actions.add(new Move(finalSolution));
		return actions;
	}




	@Override
	public List<Object> getAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Predicate> groundTruth() {
		// TODO Auto-generated method stub
		return null;
	}
}