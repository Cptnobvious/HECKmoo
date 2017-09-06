package verb.adminverbs;

import items.Item;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;

public class VerbAtItemTest extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@itemtest"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		return false;
	}

	@Override
	public String getHelpText() {
		return "Gives you a rock";
	}

}
