package playermanager;

import java.util.ArrayList;

import network.Relay;

//This holds a list of active players and takes input from the relay/sends input to the relay.
//this is part of my encapsulation please don't break it.

public class PlayerController {
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	//Add a new player when notified by relay
	public static boolean AddNewPlayer(int uID){
		players.add(new Player(uID));
		return false;
	}
	
	//remove a disconnected player and clean up their commands
	//also warn the relay that they left
	public static boolean RemovePlayerByID(int uID){
		//TODO: cleanup player's commands
		for (int i = 0; i < players.size(); i++){
			if (players.get(i).getuID() == uID){
				players.remove(i);
				Relay.RemoveClient(uID);
				return true;
			}
		}
		
		return false;
	}
	
	//remove a player that dropped connection and didn't DC cleanly
	public static boolean RemoveLostPlayer(int uID){
		return RemovePlayerByID(uID);
	}
	
	//Sends a string to the relay to send to a player
	public static boolean SendStringToPlayerByID(int uID, String str){
		Relay.SendClientString(uID, str);
		return true;
	}
	
	//Takes strings flying out of the relay and passes them along to the game logic
	public static boolean HandleStringFromClient(int uID, String str){
		System.out.println("Message from " + uID + ": " + str);
		Player ply = getPlayerByUID(uID);
		if (ply != null){
			ply.sendMessageToLogic(str);
			return true;
		}
		return false;
	}

	public static Player getPlayerByUID(int uID){
		for (int i = 0; i < players.size(); i++){
			if (players.get(i).getuID() == uID){
				return players.get(i);
			}
		}
		
		return null;
	}
	
	public static void think() {
		for (int i = 0; i < players.size(); i++){
			players.get(i).think();
		}
	}

}
