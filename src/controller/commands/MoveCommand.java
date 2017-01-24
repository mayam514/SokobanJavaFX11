package controller.commands;

import model.IModel;
import model.data.Character;
import view.IView;

public class MoveCommand extends Command {
	//Data members
	private IModel _model;
	private IView _view;
		
	//Constructor
	public MoveCommand(IModel model, IView view) {
		this._model = model;
		this._view = view;
	}
	
	
	//Override method
	@Override
	public void execute() {
		Character character = this._model.getLevel().get_characters().get(0);
		String direction = this._params.get(0);
		this._model.moveCharacter(direction, character);
		if(this._model.getLevel()._isWinner()){
			this._view.displayMessage("CONGRATULATIONS!!! You won the game :)");
		}
	}

}
