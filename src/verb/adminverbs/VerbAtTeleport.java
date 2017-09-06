package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;
import world.Zone;

public class VerbAtTeleport extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@teleport","@tele","@tp"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (!((arg.length == 3) || (arg.length == 2))){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		
		if (arg.length == 3){
			Zone zn = World.getZoneByID(arg[1]); //Get the target zone by ID.
			if (zn == null){
				ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad zone!"));
				return false;
			}
			
			int room = -1;
			
			try {
				room = Integer.parseInt(arg[2]);
			} catch (NumberFormatException e){
				sendFeedback(ply, ColorStrings.RED, "That was not a number!");
				return false;
			}
			
			
			Room rm = World.getRoom(zn.getZoneID(), room); //Get the room by zone, and then by the third argument.
			if (rm == null){
				ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad room!"));
				return false;
			}
			ply.getActor().setCurrentRoom(zn.getZoneID(), rm.getIndex()); //Set the player's current zone and room.
			ply.sendMessageToLogic("look"); //Simulate a look command.
		} else if (arg.length == 2){
			
			int room = -1;
			
			try {
				room = Integer.parseInt(arg[2]);
			} catch (NumberFormatException e){
				sendFeedback(ply, ColorStrings.RED, "That was not a number!");
				return false;
			}
			
			Room rm = World.getRoom(ply.getActor().getCurrentZone(), room);
			if (rm == null){
				ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad room!"));
				return false;
			}
			
			ply.getActor().setCurrentRoom(rm.getIndex()); //Set the player's current zone and room.
			ply.sendMessageToLogic("look"); //Simulate a look command.
		}
		return true;
	}

	@Override
	public String getHelpText() {
		
		return "@teleport <ZoneID> <RoomIndex>";
	}

}
