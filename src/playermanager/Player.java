package playermanager;

import java.util.ArrayList;

import saving.SaveManager;
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
	private boolean needsboot = false;
	
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
		if (account == null || needsboot){
			if (logingIn && !needsboot){
				if (str.toLowerCase().startsWith("create")){
					String[] nameblock = StringUtility.getWordList(str);
					account = new Account(nameblock[1], LogIn.generatePassword());
					actor = new Actor(this.uID, nameblock[1]);
					sendMessageToClient("Your account name will be: " + nameblock[1]);
					sendMessageToClient("Your password is " + account.getAccountPassword() + " please write it down.");
					sendMessageToClient("Please reconnect and log in with this information.");
					SaveManager.savePlayer(this);
					//actually it boots you now B)
					needsboot = true;
					//enterWorld();
				} else if (str.toLowerCase().startsWith(("connect"))){
					String[] args = StringUtility.getWordList(str);
					if (SaveManager.checkPlayerExists(args[1])){
						ArrayList<String> load = SaveManager.loadPlayer(args[1], args[2]);
						if (load == null) {
							sendMessageToClient("Incorrect password");
							return false;
						} else {
							sendMessageToClient("Connecting");
							//TODO initialize player
							loadPlayer(load);
							enterWorld();
						}
					} else {
						sendMessageToClient("That account does not appear to exist.");
					}
				} else {
					sendMessageToClient("I didn't understand that");
				}
			} else {
				PlayerController.RemovePlayerByID(this.uID);
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
		//TODO this is probably not the best place for this
		SaveManager.savePlayer(PlayerController.getPlayerByUID(this.uID));
	}
	
	private boolean loadPlayer(ArrayList<String> variables){
		String tag = null;
		String rest = null;
		
		account = new Account(null, null);
		actor = new Actor(this.uID, "null");
		
		for (int i = 0; i < variables.size(); i++){
			tag = StringUtility.getFirstWord(variables.get(i));
			rest = StringUtility.getStringAfterFirst(variables.get(i));
			
			if (tag.equals("$accname")){
				account.setAccountName(rest);
			} else if (tag.equals("$accpass")){
				account.setAccountPassword(rest);
			} else if (tag.equals("$actorname")){
				actor.setName(rest);
			} else if (tag.equals("$accflags")){
				if (rest != null){
					String[] flags = StringUtility.getWordList(rest);
					for (int j = 0; j < flags.length; j++){
						account.giveFlag(flags[j]);
					}
				}
			} else if (tag.equals("$actordesc")){
				actor.setDescription(rest);
			}
		}
		
		return true;
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
	
	public Account getAccount(){
		return this.account;
	}

}
