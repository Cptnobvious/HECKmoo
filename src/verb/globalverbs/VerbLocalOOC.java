package verb.globalverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbLocalOOC extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"LOOC", "["};
		alias = temp;
		return true;
	}

	@Override
	public boolean setFlags() {
		return true;
	}
	
	@Override
	public boolean run(Player ply, String str) {
		String ann = ply.getActor().getName() + ColorStrings.getColoredText(true, ColorStrings.MAGENTA, ColorStrings.BLACK, " [LOOC]") + ": ";
		ann = ann + StringUtility.getStringAfterFirst(str);
		Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ann);
		return true;
	}

	@Override
	public String getHelpText() {
		return "Send an out of character message to the room you're currently in.";
	}

}
