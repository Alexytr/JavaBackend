package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MySerialServer implements Server {
	private int port;
	private volatile boolean stop;
	private Thread thread;

	public MySerialServer(int port) {
		this.stop = false;
		this.port = port;
	}

	//Private Methods
	private void runServer(ClientHandler c) {
		try {
		ServerSocket server = new ServerSocket(this.port); // Setting a socket for the server to listen on
		server.setSoTimeout(3000); // Set timer to be 1 secound
		while(!this.stop) {
			try {
				Socket aClient = server.accept();// Waiting for a client to connect for timeout period
				try {
					c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					aClient.close();
				} catch(IOException e) {} // Coudnt read from the client
			}catch(Exception e) {} // timeOut reached without getting a clinet
		}
		server.close();
		}catch (IOException e) {	}// Coudn't connect to a socket
	}

	//Override
	@Override
	public void start(ClientHandler c) {
		this.thread = new Thread(()->runServer(c));
		this.thread.start();
	}
	@Override
	public void stop() {
		stop = true; // Closing the server.
		thread.interrupt();
	}
}
