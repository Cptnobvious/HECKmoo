package verb.globalverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;

public class VerbSay extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"say", "'"};
		alias = temp;
		return true;
	}

	@Override
	public boolean setFlags() {
		return true;
	}
	
	@Override
	public boolean run(Player ply, String str) {
		String said = ply.getActor().getName() + " says \"" + StringUtility.getStringAfterFirst(str) + "\"";
		Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), said);
		return true;
	}

	@Override
	public String getHelpText() {
		return "say <thing>";
	}

}
