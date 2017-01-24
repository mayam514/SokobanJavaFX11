package view;

import commons.Level;

public interface IView {
	public void display(Level level);
	public void displayMessage(String msg);
	void start();
}
