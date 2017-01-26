package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import commons.Level;

public class CLInew extends Observable implements IView {
	//Data members
	private BufferedReader _reader;
	private PrintWriter _writer;
	private String _exitString;
	
	//Constructor
	public CLInew(BufferedReader reader, PrintWriter writer, String exitString) {
		this._reader = reader;
		this._writer = writer;
		this._exitString = exitString;
	}
	
	@Override
	public void display(Level level) {
		this._writer.println(level);
		this._writer.flush();
	}

	@Override
	public void displayMessage(String msg) {
		this._writer.println(msg);	
		this._writer.flush();
	}

	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String commandLine = "";//Initiate the commandLine string
				do {
					_writer.print("Please enter a command: " );
					_writer.flush();
					try {
						commandLine = _reader.readLine();//Read the input
						String[] arr = commandLine.split(" ");//Split the input string by " "
						LinkedList<String> params = new LinkedList<String>();//Create a list of parameters that the user input
						for (String param : arr) {//Add the split up parameters to the linked list
							params.add(param);
						}
						setChanged();//Set an indication that there was a change
						notifyObservers(params);//Notify observers about the change
						//clearChanged();//Clear the indication
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (!commandLine.equals(_exitString));//While the user does't want to exit	
			}
		}).start();	
	}
	
}
