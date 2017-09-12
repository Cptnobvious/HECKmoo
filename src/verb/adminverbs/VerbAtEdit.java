package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbAtEdit extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@edit", "@e"};
		alias = temp;
		return true;
	}
	
	@Override
	public boolean setFlags() {
		String[] temp = {"builder", "admin"};
		flags = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length < 3){
			sendFeedback(ply, ColorStrings.RED, "Not enough arguments");
			return false;
		}
		
		//Setup the target and attribute to edit
		//First count the .'s so we don't fuck things up
		String target = null;
		String attributeName = null;
		
		int periods = StringUtility.countCharInString(args[1], '.');
		if (periods == 0){
			sendFeedback(ply, ColorStrings.RED, "You didn't specify an attribute (probably forgot the dot)");
			return false;
		} else if (periods == 1){
			
			String toBreak = args[1];
			String[] broken = toBreak.split("\\.");
			if (broken.length != 2){
				sendFeedback(ply, ColorStrings.RED, "I was unable to find that attribute after split");
				return false;
			}
			target = broken[0];
			attributeName = broken[1];
		}
		
		//Find the thing and set the value
		if (ply.getActor().getItem(target) != null){
			ply.getActor().getItem(target).setAttribute(attributeName, args[2]);
			sendFeedback(ply, ColorStrings.GREEN, "Set attribute on item");
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "@edit <thing>.<attribute> <value>";
	}

}
