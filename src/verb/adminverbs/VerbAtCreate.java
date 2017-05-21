package verb.adminverbs;

import items.Item;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;

public class VerbAtCreate extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@create"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		
		if (args.length > 2){
			if (args[1].equals("$item")){
				Item item = new Item();
				item.setName(args[2]);
				ply.getActor().addItem(item);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@create <type> <name>";
	}

}
