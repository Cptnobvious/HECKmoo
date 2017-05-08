package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;

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
			Room temp = World.getRoomByPlayer(ply);
			ply.sendMessageToClient(ColorStrings.getColoredText(true, ColorStrings.GREEN, ColorStrings.BLACK, "ROOM INDEX: " + temp.getIndex()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "IN ZONE: " + World.getZoneByPlayer(ply)));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "RoomName: " + temp.getRoomName()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "RoomDescription: " + temp.getRoomDescription()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, "RoomX: " + temp.getMapX()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, "RoomY: " + temp.getMapY()));
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.CYAN, temp.getExitNames()));
				return true;
			}
			return false;
	}

	@Override
	public String getHelpText() {
		return "@examine <room> \n Try @ex $here";
	}

}
