package verb.adminverbs;

import java.util.ArrayList;

import items.Item;
import playermanager.Player;
import script.attributes.Attribute;
import utility.ColorStrings;
import utility.StringUtility;
import verb.DynamicVerb;
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
				verb = verb + " (" + ((DynamicVerb)item.getVerb(verbs.get(i))).getScriptName() + ")";
				ply.sendMessageToClient(verb);
			}
			ArrayList<String> scripts = item.getScriptNames();
			for (int i = 0; i < scripts.size(); i++){
				String script = scripts.get(i);
				boolean compiled = item.getScript(scripts.get(i)).isCompiled();
				script = ColorStrings.getBoldText(ColorStrings.getColoredText(ColorStrings.YELLOW, script));
				if (compiled){
					script = script + ColorStrings.getColoredText(ColorStrings.GREEN, " (Compiled)");
				} else {
					script = script + ColorStrings.getColoredText(ColorStrings.RED, " (Uncompiled)");
				}
				ply.sendMessageToClient(script);
			}
		}
		
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "@audit-item <name>";
	}

}
