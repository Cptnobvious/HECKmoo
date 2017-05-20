package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;
import world.Zone;

public class VerbAtExamine extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@examine" , "@ex"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length != 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String target = arg[1]; //What are you trying to examine?
		if (target == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid target!"));
			return false;
		}
		if (target.equals("$here")){ //$here describes the room the player is in.
			Zone zn = World.getZoneByPlayer(ply); //Get the player's zone.
			Room rm = World.getRoomByPlayer(ply); //Get the player's room.
			ply.sendMessageToClient(ColorStrings.getColoredText(true, ColorStrings.GREEN, ColorStrings.BLACK, "ROOM INDEX: " + rm.getIndex()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "IN ZONE: " + zn.getZoneName()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "ZONE ID: " + zn.getZoneID()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "RoomName: " + rm.getRoomName()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "RoomDescription: " + rm.getRoomDescription()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, "RoomX: " + rm.getMapX()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, "RoomY: " + rm.getMapY()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, rm.getExitNames()));
				return true;
			}
			return false;
	}

	@Override
	public String getHelpText() {
		return "@examine <room> \n Try @ex $here";
	}

}
