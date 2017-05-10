package network;

import java.io.IOException;
import java.net.ServerSocket;

//This class sits there and watches for incoming connections

class ClientWatcher extends Thread {

	private final int PORT;
	private static ServerSocket incomingConnectionListener = null;
	private volatile boolean isRunning = false;
	private boolean isCloseRequested = false;
	
	//Create the client watcher with given port number
	public ClientWatcher(int port){
		this.PORT = port;
	}
	
	//Start the client watcher
	public void run(){
		if (!isRunning) {
			isRunning = true;
			try {
				incomingConnectionListener = new ServerSocket(PORT);
				System.out.println("Server is now listening for connections");
				
				while(!isCloseRequested){
					ClientsList.addClient(incomingConnectionListener.accept());
				}
				
			} catch (IOException e) {
				if (!isRunning){
					System.out.println("Client watcher closed");
				} else {
					System.out.println("There was a problem in the client watcher");
					e.printStackTrace();
				}
			} finally {
				try {
					incomingConnectionListener.close();
					System.out.println("Closed Client Watcher.");
				} catch (IOException e ) {
					System.out.println("Error: Problem closing the incoming connection listener!");
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Error: Tried to start the client watcher more than once!");
		}
	}
	
	public void stopClientWatcher(){
		try {
			incomingConnectionListener.close();
			//TODO Why do i have isrunning and iscloserequested?
			isRunning = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		isCloseRequested = true;
		System.out.println("Closing client watcher");
	}
	
}
