package verb.adminverbs;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbAtDescribe extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@describe", "@desc"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		String target = arg[1];
		if (target.equals("$here")){
			String description = "";
			for (int i = 2; i < arg.length; i++){
				description = description + arg[i] + " ";
			}
			World.getRoomByPlayer(ply).setRoomDescription(description);
			return true;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@describe $<thing> <description>";
	}

}
