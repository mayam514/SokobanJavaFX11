package plannable;

import java.util.ArrayList;

import commons.Level;
import strips.Clause;
import strips.Predicate;

public class PredicateLevelBuilder {
	//Data members
	private Level level;
	
	public PredicateLevelBuilder(Level level) {
		this.level = level;
	}
	
	/**
	 * The method returns a goal by the knowledgeBase
	 * @param kb the knowledgeBase
	 * @return The goal
	 */
	public static Clause getGoal(Clause kb){
		Clause goal = new Clause(null);
		for(Predicate p : kb.getPredicates()){
			if(p.getType().startsWith("finalPosition")){
				goal.add(new Predicate("boxAt", "?", p.getValue()));
			}
		}
		return goal;
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	public static Clause getKB(ArrayList<char[]> level){
		Clause kb = new Clause(null);
		int boxCount = 0;
		int targetCount = 0;
		for(int i = 0 ; i < level.size() ; i++){
			for(int j = 0 ; j < level.get(i).length ; j++){
				switch(level.get(i)[j]){
				case '#':
					kb.add(new Predicate("wallAt", "", j + "," + i));
					break;
				case ' ':
					kb.add(new Predicate("floorAt", "", j + "," + i));
					break;
				case 'A':
					kb.add(new Predicate("characterAt", "", j + "," + i));
					break;
				case '@':
					boxCount++;
					kb.add(new Predicate("boxAt", "b" + boxCount, j + "," + i));
					break;
				case 'o':
					targetCount++;
					kb.add(new Predicate("finalPositionAt", "t" + targetCount, j + "," + i));
					break;
				}
			}
		}
		return kb;		
	}
	
	/**
	 * The method reads a level file
	 * @param fileName The name of the level file
	 * @return Plannable by the level we read
	 */
	/*public static Plannable readFile(String fileName){
		try{
			
			ArrayList<char[]> level = new ArrayList<>();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
			while((line=in.readLine()) != null){
				level.add(line.toCharArray());
			}
			in.close();
			Clause kb = getKB(level);
			Clause goal = getGoal(kb); 
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			Level realLevel = getLevel(fileInputStream);
			
			Plannable plannable = new Plannable() {
				@Override//מה עושים עם הקירות????????
				public Set<ActionPlan> getsatisfyingActions(Predicate top) {
					HashSet<ActionPlan> set = new HashSet<ActionPlan>();
					if(top.getType().equals("boxAt")){
						String value = top.getValue();
						String [] pos = value.split(",");
						int x = Integer.parseInt(pos[0]);
						int y = Integer.parseInt(pos[1]);
						Move up = new Move(top.getId(), x + "," + (y-1));
						Move down = new Move(top.getId(), x + "," + (y+1));
						Move right = new Move(top.getId(), (x+1) + "," + y);
						Move left = new Move(top.getId(), (x-1) + "," + y);
						set.add(up);
						set.add(down);
						set.add(right);
						set.add(left);
					}
					return set;
				}

				@Override
				public ActionPlan getsatisfyingAction(Predicate top) {
					ActionPlan a = new ActionPlan(top.getType(), top.getId(), top.getValue());
					a.getPreconditions();

					return null;
				}
				
				@Override
				public Clause getKnowledgeBase() {
					return kb;
				}
				
				@Override
				public Clause getGoal() {
					return goal;
				}
			};			
			return plannable;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}*/
}
