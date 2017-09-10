package verb.globalverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;

public class VerbEmote extends Verb{

	@Override
	public boolean setAlias() {
		String temp[] = {"/me", ":", "emote" };
		alias = temp;
		return true;
	}

	@Override
	public boolean setFlags() {
		return true;
	}
	
	@Override
	public boolean run(Player ply, String str) {
		String said = ply.getActor().getName() + " " + StringUtility.getStringAfterFirst(str);
		Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), said);
		return true;
	}

	@Override
	public String getHelpText() {
		return "emote <text>";
	}

}
