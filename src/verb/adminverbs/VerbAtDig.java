package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;

public class VerbAtDig extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@dig"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		Room rm = new Room();
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length != 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String roomname = arg[1]; //Find the room name.
		if (roomname == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "You need to enter a room name!"));
			return false;
		}
		rm.setRoomName(roomname);
		
		int newIndex = World.getZoneByID(ply.getActor().getCurrentZone()).addRoom(rm); //Add a room to the player's current zone.
		System.out.println("Player made a new room named " + rm.getRoomName());
		ply.getActor().setCurrentRoom(newIndex); //Put the player in the new room.
		//TODO: better internal calls
		ply.sendMessageToLogic("look");
		return true;
	}

	@Override
	public String getHelpText() {
		return "@dig <roomname>";
	}

}
