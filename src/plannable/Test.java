package plannable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import adapters.PlannableLevelAdapter;
import commons.Level;
import model.data.MyTextLevelLoader;
import searchLib.Action;
import strips.ActionPlan;
import strips.Planner;
import strips.Strips;

public class Test {
	public static void main(String[] args) {
		MyTextLevelLoader loader = new MyTextLevelLoader();
		try {
			Level level = loader.loadLevel(new FileInputStream(new File("resources/level.txt")));
			
			PlannableLevelAdapter p = new PlannableLevelAdapter(level);
			
			Planner st = new Strips();
			
			List<ActionPlan> list = st.plan(p);
			
			for(ActionPlan a : list){
				System.out.println(a);
				for(Action act : ((Move)a).getSearchResult())
					System.out.println(act.getName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
