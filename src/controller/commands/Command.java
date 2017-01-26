package controller.commands;

import java.util.List;

public abstract class Command {
	//Data member
	protected List<String> _params;

	/**
	 * The method sets the parameters of every command by the specific command needs
	 * @param params the parameter we set in the _params
	 */
	public void setParams(List<String> params) {
		this._params = params;
	}
	
	/**
	 * The method execute the command
	 */
	public abstract void execute();
}
