package playermanager;

import textparser.InputTrap;
import textparser.TextParser;
import utility.StringUtility;

public class Player {
	
	//Unique ID that matches a client ID in the Relay
	private int uID;
	
	//The account of the player
	private Account account = null;
	//The actor the player is controlling
	private Actor actor = null;
	//Is the player logging in?
	private boolean logingIn = false;
	
	//Is your input getting trapped and what to do if it is
	private boolean textTrapping = false;
	private InputTrap inTrap = null;
	
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
				if (str.startsWith("create")){
					String[] nameblock = StringUtility.getWordList(str);
					account = new Account(nameblock[1], "password");
					actor = new Actor(this.uID, nameblock[1]);
					enterWorld();
				} else {
					sendMessageToClient("I didn't understand that");
				}
			}
		} else {
			if (textTrapping){
				textTrapping = inTrap.addLine(str);
				sendMessageToClient(str);
				if (!textTrapping){
					inTrap.run(this);
					sendMessageToClient("Exiting multiline input.");
				}
			} else {
				TextParser.Parse(this, str);
			}
		}
		return true;
	}
	
	public boolean startInputTrap(InputTrap trap){
		sendMessageToClient("You're now entering multiline input. Enter two empty lines to finish.");
		this.textTrapping = true;
		this.inTrap = trap;
		return true;
	}
	
	private void enterWorld(){
		sendMessageToLogic("look");
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
