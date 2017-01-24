package controller.commands;

import commons.Level;
import model.IModel;
import view.IView;

public class DisplayLevelCommand extends Command {
	//Data members
	private IModel _model;
	private IView _view;
	
	//Constructor
	public DisplayLevelCommand(IModel model, IView view) {
		this._model = model;
		this._view = view;
	}
	
	@Override
	public void execute() {
		Level level = this._model.getLevel();	
		this._view.display(level);
	}
}
