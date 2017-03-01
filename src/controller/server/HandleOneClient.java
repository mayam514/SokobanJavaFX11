package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HandleOneClient extends Observable implements ClientHandler {
	
	//Data Members
	private BlockingQueue<String> _commands;
	private boolean _stop;
	LinkedList<String> _params;
	
	//Constructor
	public HandleOneClient() {
		this._commands = new ArrayBlockingQueue<String>(30);
		this._params = new LinkedList<String>();
		this._stop = false;
		_commands.clear();
	}
	
	private void sendOutput(PrintWriter out){
		String line;

		while(!this._stop){
			try {
				line = this._commands.take();
				out.println(line);
				out.flush();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	private void readInput(BufferedReader in, String exitStr){
		try {
			String line = "";
			
			while(!_stop){//While the client didn't enter the exit string
				line = in.readLine();//get the client command
				this._params.clear();
				
				String[] arr = line.split(" ");//Split the input string by " "
				for (String param : arr) {//Add the split up parameters to the linked list
					_params.add(param);
				}
				
				try {
					_commands.put(_params.getFirst());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (line.equals(exitStr)){//If the client entered the exit string
						try {
							_commands.put("Exit");
							stop();//Set the _stop flag to true
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
						
				//Notify controller about the changes
				setChanged();
				notifyObservers(_params);
				
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thread aSyncReadInput(BufferedReader in,String exitStr){
		Thread t=new Thread(new Runnable() {
			public void run() {
				readInput(in, exitStr); 
			}
		});
		t.start();
		return t;
	}
	
	private Thread aSyncSendOutput(PrintWriter out){
		Thread t=new Thread(new Runnable() {
			public void run() {
				sendOutput(out); 
			}
		});
		t.start();
		return t;
	}

	//The method sets the _stop flag to true
	private void stop(){
		this._stop = true;
	}
	
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
		//Casting
		BufferedReader br = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter pw = new PrintWriter(outToClient);
		
		//Open the I/O threads
		Thread fromClient = aSyncReadInput(br,"Exit"); 
		Thread toClient = aSyncSendOutput(pw);
		
		try {
			fromClient.join(); //Works in parallel
			toClient.join(); //Works in parallel
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			//Close threads
			br.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}