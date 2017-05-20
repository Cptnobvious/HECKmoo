package verb.adminverbs;

import java.util.ArrayList;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.World;
import world.Zone;

public class VerbAtAuditZone extends Verb {

	@Override
	public boolean setAlias() {
		String[] temp = {"@auditzone","@adz"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if(args.length!=2){
			//Checking the correct number of arguments.
			if(args.length==3 && args[2].equals("short")){
				//If there are three arguments and the last one is "short", do the short version of @auditzone.
				Zone zone = World.getZoneByID(args[1]); //Get the zone by ID.
				int i = 0;
				String rooms = new String();
				ArrayList<String> roomlist = zone.slistAllRooms(); //Put all the rooms into an array list. (slist makes the list compact.)
				while(i<roomlist.size()){
					rooms = rooms + roomlist.get(i) + "\n"; //Putting all rooms into a single string.
					i++;
				}
				ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, rooms)); //Output the list of rooms.
				return true;
			}
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		Zone zone = World.getZoneByID(args[1]);
		if(zone == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid zone!"));
			return false;
		}
		int i = 0;
		String rooms = new String();
		ArrayList<String> roomlist = zone.listAllRooms(); //Put all rooms into an array list.
		while(i<roomlist.size()){
			rooms = rooms + roomlist.get(i) + "\n"; //Putting all rooms into a single string. 
			i++;
		}
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, rooms)); //Output the list of rooms.
		return true;
	}

	@Override
	public String getHelpText() {
		return "@auditzone <zoneid>";
	}

}
