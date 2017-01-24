package controller.commands;

import model.IModel;
import model.data.Character;

public class MoveCommand extends Command {
	//Data members
	private IModel _model;
		
	//Constructor
	public MoveCommand(IModel model) {
		this._model = model;
	}
	
	
	//Override method
	@Override
	public void execute() {
		Character character = this._model.getLevel().get_characters().get(0);
		String direction = this._params.get(0);
		this._model.moveCharacter(direction, character);
	}

}
