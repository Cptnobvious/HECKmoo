package playermanager;

import world.Room;
import world.World;

public class Player {
	
	//Unique ID that matches a client ID in the Relay
	private int uID;
	
	//The account of the player
	private Account account = null;
	//Is the player logging in?
	private boolean logingIn = false;
	
	private String currentZone = "ORGNA";
	private int currentRoom = 0;
	
	//Do update for the player
	void think(){
		if (account == null){
			if (!logingIn){
				sendMessageToClient(LogIn.getLoginScreen());
				logingIn = true;
			}
		}
	}
	
	//Send a message to the linked client
	public boolean sendMessageToClient(String str){
		PlayerController.SendStringToPlayerByID(this.uID, str);
		return true;
	}
	
	public boolean sendMessageToLogic(String str){
		if (account == null){
			if (logingIn){
				account = new Account(str, "password");
				sendMessageToClient("Sup fag");
				enterWorld();
			}
		}
		return true;
	}
	
	private void enterWorld(){
		Room rm = World.getRoom(this.currentZone, this.currentRoom);
		String str = rm.getRoomName() + "\n" + rm.getRoomDescription();
		sendMessageToClient(str);
	}
	
	Player (int uID){
		this.uID = uID;
	}
	
	public int getuID(){
		return this.uID;
	}
	
	

}
