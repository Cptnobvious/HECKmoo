package network;

import playermanager.PlayerController;

//The job of the relay class is to communicate all information coming in or going out of the network
//The relay class should only take connection/disconnection notices and strings going in or out tagged with client IDs

public class Relay {
	
	//Tells the server to add a new player
	public static boolean AddNewClient(int uID){
		PlayerController.AddNewPlayer(uID);
		return false;
	}
	
	//Tells the network handler to remove a player
	public static boolean RemoveClient(int uID){
		ClientsList.killClient(uID);
		return true;
	}
	
	//Tells the server that a player has disconnected unexpectedly
	public static boolean InformClientRemoval(int uID){
		PlayerController.RemoveLostPlayer(uID);
		return true;
	}
	
	//Tells the network to send a string to a client
	public static boolean SendClientString(int uID, String str){
		TaggedClientString tcs = new TaggedClientString(uID, str);
		ClientsList.sendMessageToClient(tcs);
		return true;
	}
	
	//Tells the server a client sent a string
	public static boolean RecieveClientString(TaggedClientString tcs){
		PlayerController.HandleStringFromClient(tcs.getUID(), tcs.getString());
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
