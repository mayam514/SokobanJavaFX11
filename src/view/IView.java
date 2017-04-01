package view;

import commons.Level;

public interface IView {
	/**
	 * The method displays the level
	 * @param level the level we want to display
	 */
	public void display(Level level);
	
	/**
	 * The method displays a message
	 * @param msg the message we want to display
	 */
	public void displayMessage(String msg);
	
	/**
	 * The method gets input from user
	 */
	public void getInput();
	
	/**
	 * The method starts the class that implements IView
	 */
	void start();
}
