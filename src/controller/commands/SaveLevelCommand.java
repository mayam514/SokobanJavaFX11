package controller.commands;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import model.IModel;
import model.data.ILevelSaver;
import model.data.MyObjectLevelSaver;
import model.data.MyTextLevelSaver;
import model.data.MyXMLLevelSaver;

public class SaveLevelCommand extends Command {
	//Data members
	private static HashMap<String, ILevelSaver> _saversMap;
	private IModel _model;
	
	//Constructor
	public SaveLevelCommand(IModel model) {
		if(_saversMap == null){
			SaveLevelCommand.init();
		}
		this._model = model;
	}
	
	/**
	 * The method initiates the savers hash map
	 */
	public static void init(){
		_saversMap = new HashMap<String, ILevelSaver>();
		_saversMap.put("txt", new MyTextLevelSaver());
		_saversMap.put("obj", new MyObjectLevelSaver());
		_saversMap.put("xml", new MyXMLLevelSaver());
	}
	
	//Override method
	@Override
	public void execute(){
		String typeOfFile = FilenameUtils.getExtension(this._params.get(0));//Get the .txt/.obj/.xml from the filename that the user typed
		ILevelSaver saver = _saversMap.get(typeOfFile);//Get the type of saver the user typed
		try {
			saver.saveLevel(this._model.getLevel(), this._params.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
