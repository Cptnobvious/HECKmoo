package playermanager;

public class Player {
	
	//Unique ID that matches a client ID in the Relay
	private int uID;
	
	//The account of the player
	private Account account = null;
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
	
	Player (int uID){
		this.uID = uID;
	}
	
	public int getuID(){
		return this.uID;
	}
	
	

}
