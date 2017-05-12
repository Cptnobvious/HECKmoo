package verb.adminverbs;

import java.util.ArrayList;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
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
			if(args.length==3 && args[2]=="short"){
				Zone zone = World.getZoneByID(args[1]);
				int i = 0;
				String rooms = new String();
				ArrayList<String> roomlist = zone.slistAllRooms();
				while(i<roomlist.size()){
					rooms = rooms + roomlist.get(i) + "\n"; 
					i++;
				}
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
		ArrayList<String> roomlist = zone.listAllRooms();
		while(i<roomlist.size()){
			rooms = rooms + roomlist.get(i) + "\n"; 
			i++;
		}
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, rooms));
		return true;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
