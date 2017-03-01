package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Command;
import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveLevelCommand;
import controller.server.MyServer;
import model.IModel;
import view.IView;

public class SokobanController implements Observer {
	//Data members
	private IModel _model;
	private IView _view;
	private Controller _controller;
	private MyServer _server;
	private Map<String, Command> _commands;
	
	//Constructor
	public SokobanController(IModel model, IView view) {
		this._model = model;
		this._view = view;
		
		this._controller = new Controller();
		this._controller.start();
		
		this.initCommands();
	}
	
	public SokobanController(IModel model, IView view, MyServer server) {
		this._model = model;
		this._server = server;
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
		MoveCommand move = new MoveCommand(this._model, this._view);
		LoadLevelCommand load = new LoadLevelCommand(this._model);
		this._commands.put("Move", move);
		this._commands.put("Display", new DisplayLevelCommand(this._model, this._view));
		this._commands.put("Load", load);
		this._commands.put("Save", new SaveLevelCommand(this._model));
		this._commands.put("Exit", new ExitCommand(this._controller));
	}
	
	@Override
	public void update(Observable o, Object arg){
		LinkedList<String> params = (LinkedList<String>) arg;//Create a linked list of the commands parameters because the controller gets only linkedlist
		String commandKey="";
		if(params!=null){
			commandKey = params.removeFirst();//Get the name of the command
		}
		Command c = this._commands.get(commandKey);//Get the command by the command name
		if (c == null) {
			this._view.displayMessage("Command not found");
			return;
		}
		c.setParams(params);
		this._controller.insertCommand(c);//Insert command to the BlockingQueue
	}
}
