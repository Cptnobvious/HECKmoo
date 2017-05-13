package playermanager;

import textparser.TextParser;
import world.World;

public class Player {
	
	//Unique ID that matches a client ID in the Relay
	private int uID;
	
	//The account of the player
	private Account account = null;
	//The actor the player is controlling
	private Actor actor = null;
	//Is the player logging in?
	private boolean logingIn = false;
	
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
	
	//send a message from the client over to logic
	public boolean sendMessageToLogic(String str){
		//System.out.println("Sending a message to logic");
		if (account == null){
			if (logingIn){
				account = new Account(str, "password");
				actor = new Actor(this.uID, str);
				sendMessageToClient("Sup fag");
				enterWorld();
			}
		} else {
			TextParser.Parse(this, str);
		}
		return true;
	}
	
	private void enterWorld(){
		String str = World.getRoomLook(actor.getCurrentZone(), actor.getCurrentRoom());
		sendMessageToClient(str);
	}
	
	private boolean isLoggingIn(){
		return logingIn;
	}
	
	Player (int uID){
		this.uID = uID;
	}
	
	public int getuID(){
		return this.uID;
	}
	
	public Actor getActor(){
		return this.actor;
	}

}
