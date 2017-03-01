package controller.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler{
	/**
	 * The method defines the interaction between the clients and the server
	 * @param inFromClient the input we get from the client
	 * @param outToClient the output we send to the client
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient);
}