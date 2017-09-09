package verb.adminverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbAtAShout extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@shout"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		
		String ann = "\n" + ply.getActor().getName() + " shouts from on high \"" + StringUtility.getStringAfterFirst(str).toUpperCase() + "\"\n";
		ann = ColorStrings.getColoredText(true, ColorStrings.WHITE, ColorStrings.BLACK, ann);
		Announcement.announceGlobalRaw(ann);
		
		return true;
	}

	@Override
	public String getHelpText() {
		return "@shout <angry message for players>";
	}

}
