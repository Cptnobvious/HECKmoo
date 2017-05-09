package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import verb.Verb;

public class VerbAtPalette extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@palette"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		ply.sendMessageToClient(ColorStrings.getPallet());
		return true;
	}

	@Override
	public String getHelpText() {
		return "Call this to see all possible colors";
	}

}
