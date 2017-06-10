package adapters;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import commons.Level;
import model.data.Box;
import model.data.FinalPosition;
import model.data.Position;
import plannable.Move;
import plannable.SokobanPredicate;
import searchLib.Action;
import searchLib.BFS;
import searchLib.Solution;
import strips.ActionPlan;
import strips.Clause;
import strips.Plannable;
import strips.Predicate;

public class PlannableLevelAdapter implements Plannable{
	//Data members
	private Level level;
	private List<FinalPosition> finalPositions;
	private List<Box> boxes;
	private HashMap<String, Box> unhandledBoxes;
	private Random random;
	private char[][] permanentItemsBoard;

	//Constructor
	public PlannableLevelAdapter(Level level) {
		this.level = level;
		this.finalPositions = level.get_finalPositions();
		this.boxes = level.get_boxes();
		this.unhandledBoxes = new HashMap<String, Box>();
		this.random = new Random();
		this.permanentItemsBoard = getBoardWithPermanentItems();
	}

	/**
	 * The method returns a board with all the unmoveable items (without boxes and characters)
	 * @return The new board without boxes and characters
	 */
	private char[][] getBoardWithPermanentItems() {
		char[][] board = level.getLevelAsBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'A' || board[i][j] == '@'){
					board[i][j] = ' ';
				}
			}
		}
		return board;
	}
	
	/**
	 * The method arranges the final positions in the queue by the shortest path between the character and each final position
	 * @return PriorityQueue of FinalPositions by the shortest distance from the character
	 */
	private PriorityQueue<FinalPosition> heuristicalComparator() {
		PriorityQueue<FinalPosition> priorityQueue = new PriorityQueue<>(new Comparator<FinalPosition>() {
			
			@Override
			public int compare(FinalPosition fp1, FinalPosition fp2) {
				Position characterPosition = level.get_characters().get(0).get_position();
				return getManhattanDistance(characterPosition, fp1.get_position()) - getManhattanDistance(characterPosition, fp2.get_position());
			}
		});
		
		for(FinalPosition fp : finalPositions){
			priorityQueue.add(fp);
		}
		
		return priorityQueue;
	}
	
	/**
	 * The method calculates the manhattan distance between a character and a finalPosition
	 * @param character The position of the character
	 * @param finalPosition The position of the finalPosition
	 * @return The manhattan distance between a box and a finalPosition
	 */
	private int getManhattanDistance(Position character, Position finalPosition){
		int subX = Math.abs(character.getP_x() - finalPosition.getP_x());
		int subY = Math.abs(character.getP_y() - finalPosition.getP_y());
		return subX + subY;
	}
	
	/**
	 * The method converts a string that symbolize postion to a Position type
	 * @param pos The string we want to convert
	 * @return The Position we converted
	 */
	public Position convertFromStringToPosition(String pos) {
		String s = pos.replace("(", "");
		s = s.replace(")", "");
		String[] arr = s.split(",");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);

		return new Position(x,y);
	}
	
	/**
	 * The method updates the board and add the player and boxes at their current positions according to the knowledgebase
	 * @param knowledgeBase The set of predicates of the current state of the board
	 * @return The new board
	 */
	public char[][] updateBoardByKnowledgeBase(Clause knowledgeBase) {
		char[][] stateBored = copyBoard(permanentItemsBoard);
		Position position;

		for (Predicate p : knowledgeBase.getPredicates()) {
			if (p.getType() == "boxAt" && p.getId() != "?") {
				position = convertFromStringToPosition(p.getValue());
				stateBored[position.getP_x()][position.getP_y()] = '@';
			}
			if (p.getType() == "characterAt") {
				position = convertFromStringToPosition(p.getValue());
				stateBored[position.getP_x()][position.getP_y()] = 'A';
			}
		}
		return stateBored;
	}
	
	/**
	 * The method copies a board
	 * @param board The board we want to copy
	 * @return The new copied board
	 */
	public char[][] copyBoard(char[][] board) {
		char[][] newBoard = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
	
	/**
	 * The method gets an action and the current position of the character and returns the new position according to the action direction
	 * @param action The action that says where we want to move the character to
	 * @param currPos The current position of the character
	 * @return The new position of the character
	 */
	public Position getCharacterPositionAfterAction(Action action, Position currPos) {
		if(action != null){
			String actionName = action.getName();
			int x = currPos.getP_x(), y = currPos.getP_y();
			if(actionName.equals("move up")){
				return new Position(x , y+1);
			}
			else if(actionName.equals("move down")){
				return new Position(x , y-1);
			}
			else if(actionName.equals("move left")){
				return new Position(x+1 , y);
			}
			else if(actionName.equals("move right")){
				return new Position(x-1 , y);
			}
		}
		return null;
	}
	
	@Override
	public Clause getGoal() {
		PriorityQueue<FinalPosition> finalPositionsQueue = heuristicalComparator();
		Clause goal = new Clause(null);
		Predicate predicate;
		FinalPosition finalPositions;

		while(!finalPositionsQueue.isEmpty()){
			finalPositions = finalPositionsQueue.poll();
			predicate = new SokobanPredicate("boxAt", "?", finalPositions.get_position().toString());
			goal.add(predicate);
		}

		return goal;
	}

	@Override
	public Clause getKnowledgeBase() {
		Clause knowledgebase = new Clause(null);
		Predicate predicate;

		//Add character with his position to the knowledgebase
		predicate = new SokobanPredicate("characterAt", "c1", level.get_characters().get(0).get_position().toString());
		knowledgebase.add(predicate);

		//Add all final positions to the knowledgebase
		for (FinalPosition fp : finalPositions) {
			predicate = new SokobanPredicate("clear", "?", fp.get_position().toString());
			knowledgebase.add(predicate);
		}

		//Add all boxes to the knowledgebase
		for(int i = 0 ; i < boxes.size() ; i++){
			predicate = new SokobanPredicate("boxAt", "b" + i, boxes.get(i).get_position().toString());
			knowledgebase.add(predicate);
			this.unhandledBoxes.put("b" + i, boxes.get(i));
		}

		return knowledgebase;
	}
	
	@Override
	public Set<ActionPlan> getsatisfyingActions(Predicate top) {
		return null;
	}

	@Override
	public ActionPlan getsatisfyingAction(Predicate predicate, Clause knowledgeBase) {

		BFS<Position> search = new BFS<Position>();
		Position goalPosition = convertFromStringToPosition(predicate.getValue());
		Solution solution = null;
		Position initPosition = null;
		String boxId = null;
		int index;

		while (solution == null){
			index = random.nextInt(boxes.size());
			boxId = "b" + index;
			Box chosenBox = unhandledBoxes.get(boxId);
			if (chosenBox != null) {
				initPosition = chosenBox.get_position();//The initial position will be the chosen box position
				SearchablePushAdapter searchablePushAdapter = new SearchablePushAdapter(initPosition, goalPosition, updateBoardByKnowledgeBase(knowledgeBase));
				solution = search.search(searchablePushAdapter);//Get the shortest path for the box to the final position
			}
		}

		if (solution != null) {
			unhandledBoxes.remove(boxId);
			Move move = new Move(boxId, goalPosition.toString(), solution.getActionsForSolution());//Creates new move according to the path solution
			move.setPreconditions(new Clause(new Predicate("clear", "?", goalPosition.toString())));
			Position characterPosition = getCharacterPositionAfterAction(solution.getActionsForSolution().get(solution.getActionsForSolution().size()-1), goalPosition);
			Clause effects = new Clause(new Predicate("boxAt", boxId, goalPosition.toString()), new Predicate("characterAt", "c1", characterPosition.toString()));
			move.setEffect(effects);
			move.setDeleteEffects(new Clause(new Predicate("clear", "?", goalPosition.toString())));
			return move;
		}
		return null;
	}
	
}