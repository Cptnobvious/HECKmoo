package verb.globalverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbExAlias extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"!alias"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String name = StringUtility.getStringAfterFirst(str);
		if (StringUtility.isValidName(name, 20)){
			ply.getActor().setName(name);
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "Name set!"));
			return true;
		}
		
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid Name!"));
		return false;
	}

	@Override
	public String getHelpText() {
		return "!alias <name>";
	}

}
