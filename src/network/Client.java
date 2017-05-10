package network;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

//This class holds information about a client and allows receiving/sending messages to them

class Client {

	//TODO: solve this problem
	//Max size of the buffered reader (in bytes? What is this?)
	private static final int INPUTBUFFERSIZE = 2048;
	//Socket information about the client
	private Socket cSocket;
	//Reads input from the socket
	private BufferedReader in;
	//Sends input to the client
	private PrintWriter out;
	//Tracks if this client is alive
	private volatile boolean clientAlive = true;
	//List of strings to pass to the relay
	private volatile ArrayList<String> toRelay = new ArrayList<String>();
	//List of strings to pass to the client
	private volatile ArrayList<String> toClient = new ArrayList<String>();
	//Unique ID for use by ClientsList
	private int uID;
	
	//Make a new client with a given socket and unique ID
	public Client(Socket socket, int uID){
		this.cSocket = socket;
		this.uID = uID;
	}
	
	//Initialize the client object.
	public boolean init(){
		
		System.out.println("Trying to initialize a new client");
		
		if (cSocket != null){
			try {
				System.out.println("Client connected from: " + cSocket.getInetAddress() + " is now ID " + uID);
				
				
				in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()), Client.INPUTBUFFERSIZE);
				out = new PrintWriter(cSocket.getOutputStream(), true);
				
				clientAlive = true;
				return true;
				
			} catch (IOException e){
				System.out.println("There was a problem initializing this client.");
				e.printStackTrace();
			} 
		}
		
		return false;
	}
	
	//Close connection with the client
	public boolean killClient(){
		try {
			cSocket.close();
			in.close();
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("There was a problem killing a client io stream");
			e.printStackTrace();
			return false;
		}
	}
	
	//Send strings to and from the client, check if it's still alive
	public void think() {
		if (clientAlive){
			try {
				
				//Grab strings coming from the client and hold them
				String input;
				
				//Grabs a single line if one exists, this might need reworking (and it might block)
				//TODO: make this nonblocking
				while (in.ready()){
					input = in.readLine();
					if (input == null){
						clientAlive = false;
					} else {
						toRelay.add(input);
					}
				}
				
				//Add tags and send those strings off to the relay
				for (int i = 0; i < toRelay.size(); i++){
					TaggedClientString tcs = new TaggedClientString(this.uID, toRelay.get(i));
					sendToRelay(tcs);
				}
				toRelay.clear();
				
				//Send strings to the client
				if (!out.checkError()){
					if (!toClient.isEmpty()){
						for (int i = 0; i < toClient.size(); i++){
							out.println(toClient.get(i));
						}
						toClient.clear();
					}
				} else {
					clientAlive = false;
				}
			} catch (IOException e) {
				clientAlive = false;
				//TODO: verbose this
				//e.printStackTrace();
			}
			
			//Close the client if it's not alive anymore
			if (!clientAlive){
				Relay.InformClientRemoval(this.uID);
				killClient();
			}
		}
	}
	
	//Send stuff to the relay
	private void sendToRelay(TaggedClientString tcs){
		Relay.RecieveClientString(tcs);
	}
	
	//Send stuff to the client
	public void sendToClient(String str){
		toClient.add(str);
	}
	
	//Get the unique ID
	public int getUniqueID(){
		return this.uID;
	}
}
