package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Command;
import controller.commands.DisplayLevelCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import model.IModel;
import view.IView;

public class SokobanController implements Observer {
	//Data members
	private IModel _model;
	private IView _view;
	private Controller _controller;
	private Map<String, Command> _commands;
	
	//Constructor
	public SokobanController(IModel model, IView view) {
		this._model = model;
		this._view = view;
		
		this._controller = new Controller();
		this._controller.start();
		
		this.initCommands();
	}
	
	/**
	 * The method initiates the commands map
	 */
	private void initCommands(){
		this._commands = new HashMap<String, Command>();
		MoveCommand move = new MoveCommand(this._model);
		LoadLevelCommand load = new LoadLevelCommand(this._model);
		this._commands.put("Move", move);
		this._commands.put("Display", new DisplayLevelCommand(this._model, this._view));
		this._commands.put("Load", load);
		this._commands.put("Save", new SaveLevelCommand(this._model));
		//this._commands.put("Exit", new ExitCommand(this._model));
	}
	
	@Override
	public void update(Observable o, Object arg){
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey="";
		if(params!=null){
			commandKey = params.removeFirst();
			System.out.println("command key :"+commandKey);
		}
		Command c = this._commands.get(commandKey);
		if (c == null) {
			System.out.println("aaa");
			this._view.displayMessage("Command not found");
			return;
		}
		c.setParams(params);
		this._controller.insertCommand(c);
	}
}
