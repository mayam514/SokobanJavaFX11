package controller.commands;

import controller.Controller;

//The command will exit the program
public class ExitCommand extends Command {
	//Data member
	private Controller _controller;
	
	//Constructor
	public ExitCommand(Controller controller) {
		this._controller = controller;
	}
	
	@Override
	public void execute() {
		/*if(server != null){//Close the server
			server.close();
		}*/
		
		this._controller.stop();//Stop the controller threads
		
		System.exit(0);//The program ends successfully
	}
}

