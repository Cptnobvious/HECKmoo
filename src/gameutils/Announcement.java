package gameutils;

import java.util.ArrayList;

import playermanager.Player;
import playermanager.PlayerController;

//Says a thing to everyone in a room basically

public class Announcement {

	public static boolean announceToRoomRaw(String zone, int room, String announcement){
		ArrayList<Player> players = PlayerController.getPlayersListCopy();
		
		for (int i = 0; i < players.size(); i++){
			if (players.get(i).getActor() != null){
				if (players.get(i).getActor().getCurrentRoom() == room){
					if (players.get(i).getActor().getCurrentZone().equals(zone)){
						players.get(i).sendMessageToClient(announcement);
					}
				}
			}
		}
		
		return true;
	}
	
}
