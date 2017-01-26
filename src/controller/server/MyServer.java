package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	private int port;
	private ClientHandler ch;
	private volatile boolean stop;

	public MyServer(int port,ClientHandler ch) {
		this.port=port;
		this.ch=ch;
		stop=false;
	}

	
	private void runServer()throws Exception {
		System.out.println("Server is alive and waiting for the client");
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop){
			try{
				Socket aClient=server.accept();
				InputStream is = aClient.getInputStream();
				OutputStream pw = aClient.getOutputStream();
				// blocking call
				ch.handleClient(is, pw);
				is.close(); 
				aClient.getOutputStream().close();
				aClient.close();
				} catch (IOException e) {/*...*/}
			}
		server.close(); //WOW! we should wait for all threads before closing! }
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
		stop=true;
	}
}