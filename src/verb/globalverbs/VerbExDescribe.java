package verb.globalverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbExDescribe extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"!describe", "!desc"};
		alias = temp;
		return true;
	}
	
	@Override
	public boolean setFlags() {
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordList(str);
		if (args[1].equals("$me")){
			String desc = "";
			for (int i = 2; i < args.length; i++){
				desc = desc + args[i] + " ";
			}
			ply.getActor().setDescription(desc);
			sendFeedback(ply, ColorStrings.GREEN, "Description changed");
		} else {
			sendFeedback(ply, ColorStrings.RED, "You have tried to set the description for something beyond your scope.");
		}
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "!describe $me <description>";
	}

}
