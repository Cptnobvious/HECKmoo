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
		String target = arg[1];
		if (target.equals("$here")){
			Zone zn = World.getZoneByPlayer(ply);
			Room rm = World.getRoomByPlayer(ply);
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
