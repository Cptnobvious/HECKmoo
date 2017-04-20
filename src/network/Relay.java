package network;

//The job of the relay class is to communicate all information coming in or going out of the network
//The relay class should only take connection/disconnection notices and strings going in or out tagged with client IDs

public class Relay {
	
	//Tells the server to add a new player
	public static boolean AddNewClient(){
		//TODO: inform server someone just came in
		return false;
	}
	
	//Tells the network handler to remove a player
	public static boolean RemoveClient(int uID){
		ClientsList.killClient(uID);
		return true;
	}
	
	//Tells the server that a player has disconnected
	public static boolean InformClientRemoval(int uID){
		//TODO: tell the server someone's connection is now dead
		return true;
	}
	
	//Tells the network to send a string to a client
	public static boolean SendClientString(TaggedClientString tcs){
		ClientsList.sendMessageToClient(tcs);
		return true;
	}
	
	//Tells the server a client sent a string
	public static boolean RecieveClientString(TaggedClientString tcs){
		// tell the server shit here
		System.out.println("Message from " + tcs.getUID() + ": " + tcs.getString());
		
		//echo back as test
		//hot damn did that fuck it up
		SendClientString(tcs);
		return false;
	}
	
	//Start the network
	public static boolean StartNetwork(int port){
		return Network.StartNetwork(port);
	}
	
	//Close the network
	public static boolean CloseNetwork(){
		return Network.CloseNetwork();
	}
	
	//Tell the server to do stuff
	public static boolean think(){
		Network.think();
		return true;
	}
	
}
