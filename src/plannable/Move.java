package plannable;

import java.util.ArrayList;
import java.util.List;

import adapters.SearchableMoveAdapter;
import commons.Level;
import model.data.Position;
import searchLib.BFS;
import searchLib.Solution;
import strips.ActionPlan;
import strips.Predicate;

public class Move extends ActionPlan{
	//Data members
	protected List<Predicate> preconditions = new ArrayList<Predicate>();
	protected List<Predicate> effects = new ArrayList<Predicate>();
	private Solution finalSolution;
	private Level level;

	//Constructor
	public Move(String id, String value) {
		super("move", id, value);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The method activates the move functionality
	 */
	public void execute() {
		SearchableMoveAdapter a = new SearchableMoveAdapter(level, characterTarget);
		BFS<Position> bfs = new BFS<Position>();
		Solution sol = bfs.search(a);

		finalSolution.getActions().addAll(sol.getActions());
	}
	
}
