package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//Data Members
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;

	//Constructor
	public MyServer(int port,ClientHandler ch) {
		this.port=port;
		this.ch=ch;
		stop=false;
	}
	
	private void runServer()throws Exception {
		//Opens the server's socket that waits for new client's connection
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Server is alive and waiting for the client");
		//Set socket timer
		server.setSoTimeout(1000000);
		
		while(!stop){//While the client didn't enter the exit string
			try{
				//Three hand-shake TCP. Opens the socket between the server and the client
				Socket aClient = server.accept();
				//Get the client input
				InputStream is = aClient.getInputStream();
				//Get the client output
				OutputStream pw = aClient.getOutputStream();
				//Activates the connection between the server and the client
				ch.handleClient(is, pw);
				
				//Close inputStream, outputStream and the sockets
				is.close(); 
				aClient.getOutputStream().close();
				aClient.close();
				} catch (IOException e) {/*...*/}
			}
		//Closes the server's socket
		server.close(); 
	}
	
	public void start(){
		new Thread(new Runnable() {
			public void run() {
				try{
					runServer();
				}catch (Exception e){}
				}
			}).start();

	}

	public void stop(){
		stop = true;
	}
}