package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.DynamicVerb;
import verb.Verb;

public class VerbAtVerb extends Verb{
	@Override
	public boolean setAlias() {
		String[] temp = {"@verb"};
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
		if (args.length != 3){
			sendFeedback(ply, ColorStrings.RED, true, "Not enough arguments!");
			return false;
		}
		
		//Setup the target and attribute to edit
		//First count the :'s so we don't fuck things up
		String target = null;
		String scriptVerb = null;
		
		int colons = StringUtility.countCharInString(args[1], ':');
		if (colons == 0){
			sendFeedback(ply, ColorStrings.RED, true, "No verb target! (You probably forgot something after the colon)");
			return false;
		} else if (colons == 1){
			
			String toBreak = args[1];
			String[] broken = toBreak.split(":");
			if (broken.length != 2){
				return false;
			}
			target = broken[0];
			scriptVerb = broken[1];
		}
		
		//Find the thing and set the value
		if (ply.getActor().getItem(target) != null){
			DynamicVerb verb = new DynamicVerb(ply.getActor().getItem(target), args[2]);
			verb.setAlias(scriptVerb);
			ply.getActor().getItem(target).addVerb(verb);
			sendFeedback(ply, ColorStrings.GREEN, true, "Added verb " + verb.getAliases()[0] + " to " + ply.getActor().getItem(target).getName());
			return true;
		}
		
		return false;
	}
	
	@Override
	public String getHelpText() {
		return "@verb <target>:<verb name> <script>";
	}
}
