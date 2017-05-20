package verb.adminverbs;

import java.util.ArrayList;

import items.Item;
import playermanager.Player;
import script.attributes.Attribute;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbAtAuditItem extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@audititem", "@audit-item"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length < 2){
			return false;
		}
		
		//Find the item first
		Item item = null;
		//Check inventory
		item = ply.getActor().getItem(args[1]);
		if (item != null){
			ArrayList<Attribute> al = item.getAttributeList();
			for (int i = 0; i < al.size(); i++){
				String name 	= al.get(i).getName();
				name = ColorStrings.getColoredText(ColorStrings.CYAN, name);
				String value 	= al.get(i).sGetValue();
				value = ColorStrings.getColoredText(ColorStrings.GREEN, value);
				String out = name + ": " + value;
				ply.sendMessageToClient(out);
			}
			ArrayList<String> verbs = item.sGetVerbList();
			for (int i = 0; i < verbs.size(); i++){
				String verb = verbs.get(i);
				verb = ColorStrings.getColoredText(ColorStrings.MAGENTA, verb);
				ply.sendMessageToClient(verb);
			}
		}
		
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "@audit-item <name>";
	}

}
