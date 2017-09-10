package verb.globalverbs;

import chat.ChatMaster;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;

public class VerbExChat extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"!chat"};
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
		if (args.length != 3){
			sendFeedback(ply, ColorStrings.RED, false, "Invalid number of arguments.");
			return false;
		}
		
		//Check if that channel exists
		if (!ChatMaster.doesChannelExist(args[1])){
			sendFeedback(ply, ColorStrings.RED, false, "That channel doesn't exist.");
			return false;
		} else {
			if (args[2].toLowerCase().equals("on")){
				ply.getAccount().turnChannelOn(args[1]);
				sendFeedback(ply, ColorStrings.GREEN, true, "Channel activated.");
			} else {
				ply.getAccount().turnChannelOff(args[1]);
				sendFeedback(ply, ColorStrings.GREEN, true, "Channel deactivated.");
			}
		}
		
		return true;
	}

	@Override
	public String getHelpText() {
		return "!chat <channel> <on/off>";
	}

}
