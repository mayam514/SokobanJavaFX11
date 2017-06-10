package plannable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
			String fileName = "resources/level1.txt";
			Level level = loader.loadLevel(new FileInputStream(new File(fileName)));
			
			PlannableLevelAdapter p = new PlannableLevelAdapter(level);
			
			Planner st = new Strips();
			
			List<ActionPlan> list = st.plan(p);
			
			FileWriter fw = new FileWriter("resources/levelSolution.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(ActionPlan a : list){
				System.out.println(a);
				bw.write(a.toString());
				bw.write(System.lineSeparator());
				for(Action act : ((Move)a).getSearchResult()){
					System.out.println(act.getName());
					bw.write(act.getName());
					bw.write(System.lineSeparator());
				}
			}
			if (bw != null){
				bw.close();
			}
			if (fw != null){
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
