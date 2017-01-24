package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import model.IModel;
import model.data.ILevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXMLLevelLoader;

public class LoadLevelCommand extends Command {
	//Data members
	private IModel _model;
	private static HashMap <String, ILevelLoader> _loadersMap;
	
	//Constructor
	public LoadLevelCommand(IModel model) {
		if(_loadersMap == null){//Make sure that the loaders map will initiate one time only
			LoadLevelCommand.init();
		}
		this._model = model;
	}
	
	
	/**
	 * The method initiates the loaders hash map
	 */
	private static void init(){
		_loadersMap = new HashMap <String, ILevelLoader>();
		_loadersMap.put("txt", new MyTextLevelLoader());
		_loadersMap.put("obj", new MyObjectLevelLoader());
		_loadersMap.put("xml", new MyXMLLevelLoader());
	}
	
	//Override method
	@Override
	public void execute(){
		System.out.println("path: "+this._params.get(0));
		String typeOfFile = FilenameUtils.getExtension(this._params.get(0));//Get the .txt/.obj/.xml from the filename that the user typed
		ILevelLoader loader = _loadersMap.get(typeOfFile);//Get the type of loader the user typed
		InputStream fileInputStream = null;
		try{
			fileInputStream = new FileInputStream(this._params.get(0));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		this._model.setLevel(loader.loadLevel(fileInputStream));
	}
}
