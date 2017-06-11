package plannable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import adapters.PlannableLevelAdapter;
import commons.Level;
import model.data.ILevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXMLLevelLoader;
import searchLib.Action;
import strips.ActionPlan;
import strips.Planner;
import strips.Strips;

public class SokobanSolver {
	public static void main(String[] args) {
	//	MyTextLevelLoader loader = new MyTextLevelLoader();
		try {
			//String fileName = "resources/level1.txt";
			
			HashMap <String, ILevelLoader> _loadersMap;
			
			_loadersMap = new HashMap <String, ILevelLoader>();
			_loadersMap.put("txt", new MyTextLevelLoader());
			_loadersMap.put("obj", new MyObjectLevelLoader());
			_loadersMap.put("xml", new MyXMLLevelLoader());
			
			Path p = Paths.get(args[0]);
			String fileNameWithExtentions = p.getFileName().toString();
			String[] fileName = fileNameWithExtentions.split("[.]");
			String typeOfFile = FilenameUtils.getExtension(args[0]);//Get the .txt/.obj/.xml from the filename that the user typed
			ILevelLoader loader = _loadersMap.get(typeOfFile);//Get the type of loader the user typed
			Level level = loader.loadLevel(new FileInputStream(new File(args[0])));
			
			PlannableLevelAdapter pp = new PlannableLevelAdapter(level);
			
			Planner st = new Strips();
			
			List<ActionPlan> list = st.plan(pp);
			
			FileWriter fw = new FileWriter("resources/levelSolution.txt");
			//FileWriter fw = new FileWriter(args[1]);
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
