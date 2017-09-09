package verb.adminverbs;

import playermanager.Player;
import playermanager.PlayerController;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbAtGiveFlag extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@giveflag"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordList(str);
		
		if (PlayerController.getPlayerByActorName(args[1]) != null){
			PlayerController.getPlayerByActorName(args[1]).getAccount().giveFlag(args[2]);
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, "Gave flag " + args[2] + " to player " + args[1]));
			return true;
		}
		
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Could not find player!"));
		
		return false;
	}

	@Override
	public String getHelpText() {
		return "@giveflag <player> <flag>";
	}

}
