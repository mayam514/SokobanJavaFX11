package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;

public class HandleOneClient extends Observable implements ClientHandler {

	private void readInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		try {
			String line;
			while(!(line=in.readLine()).equals(exitStr)){
				setChanged();
				notifyObservers(line);
				out.println(line);
				out.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		Thread t=new Thread(new Runnable() {
			public void run() {
				readInputsAndSend(in, out, exitStr); 
			}
		});
		t.start();
		return t;
	}
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		BufferedReader br = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter pw = new PrintWriter(outToClient);
		
		Thread t = aSyncReadInputsAndSend(br,pw,"exit"); // different thread

		setChanged();
		notifyObservers();
		
		try {
			t.join();
			
		} catch (InterruptedException e) {
			/*...*/
		}
		
		try {
			br.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}