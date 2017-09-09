package verb.globalverbs;

import java.util.ArrayList;

import playermanager.Player;
import playermanager.PlayerController;
import verb.Verb;
import world.World;

public class VerbWho extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"who"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		ArrayList<Player> players = PlayerController.getPlayersListCopy();
		
		//Setup 
		ply.sendMessageToClient("Name" + getSpaces(16) + "Location");
		ply.sendMessageToClient("--------------------------------------------------------------------------------");
		for (int i = 0; i < players.size(); i++){
			String name = players.get(i).getActor().getName();
			name = name + (getSpaces(20 - name.length()));
			String loc = World.getRoomByPlayer(players.get(i)).getRoomName() + " (" + World.getZoneByPlayer(players.get(i)).getZoneName() + ")";
			ply.sendMessageToClient(name + loc);
		}
		
		return true;
	}

	@Override
	public String getHelpText() {
		return "Get a helpful list of everyone on the server";
	}

	private String getSpaces(int t){
		if (t <= 0) {
			return "";
		}
		
		String spaces = "";
		for (int i = 0; i < t; i++){
			spaces = spaces + " ";
		}
		return spaces;
	}
	
}
