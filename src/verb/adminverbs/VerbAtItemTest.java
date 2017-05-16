package verb.adminverbs;

import items.items.Rock;
import playermanager.Player;
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
		ply.getActor().addItem(new Rock());
		return true;
	}

	@Override
	public String getHelpText() {
		return "Gives you a rock";
	}

}
