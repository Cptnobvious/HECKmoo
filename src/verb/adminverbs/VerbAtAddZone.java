package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;
import world.Zone;

public class VerbAtAddZone extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@addzone","@az"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		//TODO: error catching
		Zone zn = new Zone();
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length > 3){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Too many arguments!"));
			return false;
		}
		String zonename = args[1];
		String zoneid = args[2];
		if (zonename == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "You need to enter a zone name!"));
			return false;
		}
		if (zoneid.length() != 5){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "INVALID ZONEID\n(ZoneID must be 5 letters!)"));
			return false;
		}
		zn.setZoneName(zonename);
		zn.setZoneID(zoneid);
		System.out.println("Player made a new zone named " + zn.getZoneName());
		Room rm = new Room();
		rm.setRoomName("NEW ZONE");
		zn.addRoom(rm);
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "Added new zone: " + zonename));
		return true;
	}

	@Override
	public String getHelpText() {
		
		return "@addzone <zonename> <zoneid>";
	}

}
