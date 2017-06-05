package plannable.action;

import java.util.ArrayList;
import java.util.List;

import adapters.SearchableMoveAdapter;
import commons.Level;
import model.data.Box;
import model.data.Position;
import plannable.predicate.ClearPathPredicate;
import plannable.predicate.OnPredicate;
import plannable.predicate.Predicate;
import searchLib.BFS;
import searchLib.Solution;
import model.data.Character;

public class Move extends Action {
	//Data members
	private Solution finalSolution;
	private Level level;
	private Character character;
	private Box box;
	private Position characterTarget;

	//Constructor
	public Move(Solution finalSolution, Level level, Character character, Box box, Position characterFinalPosition) {
		this.name = "Move";
		this.finalSolution = finalSolution;
		this.level = level;
		this.character = character;
		this.box = box;
		this.characterTarget = characterFinalPosition;
		
		args = new Object[]{character, box, characterFinalPosition};
		preconditions.add(new ClearPathPredicate("ClearPathPredicate", args, level));
		args = new Object[]{character, character.get_position()};
		preconditions.add(new OnPredicate("OnPredicate", args));
		preconditions.add(new Predicate("Clear", "y"));
		addList.add(new Predicate("On", "b", "y"));
		addList.add(new Predicate("Clear", "x"));
		deleteList.add(new Predicate("On", "b", "x"));
		deleteList.add(new Predicate("Clear", "y"));

		List<Object> values = new ArrayList<Object>();
		values.add("Table");
		illegalAssignments.put("b", values);
	}

	@Override
	public void execute() {
		SearchableMoveAdapter a = new SearchableMoveAdapter(level, characterTarget);
		BFS<Position> bfs = new BFS<Position>();
		Solution sol = bfs.search(a);

		finalSolution.getActions().addAll(sol.getActions());
	}

}