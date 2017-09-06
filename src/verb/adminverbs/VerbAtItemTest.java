package verb.adminverbs;

import items.Item;
import items.items.Rock;
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
//		Item item = new Rock();
//		if (StringUtility.getWordListWithoutQuotes(str).length > 1){
//			item.setName("Big Rock");
//			item.setDescription("This rock is bigger than normal");
//		}
//		ply.getActor().addItem(item);
//		return true;
		return false;
	}

	@Override
	public String getHelpText() {
		return "Gives you a rock";
	}

}
