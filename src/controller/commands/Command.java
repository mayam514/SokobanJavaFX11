package controller.commands;

import java.util.List;

public abstract class Command {
	protected List<String> _params;

	public void setParams(List<String> params) {
		this._params = params;
	}
	
	public abstract void execute();
}
