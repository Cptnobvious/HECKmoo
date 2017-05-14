package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbAtDescribe extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@describe", "@desc"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length < 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			//Checking for errors in the arguments.
			return false;
		}
		String target = arg[1];
		if (target == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid target!"));
			//Checking that a target exists.
			return false;
		}
		if (target.equals("$here")){ //Are you describing the room you're in?
			String description = "";
			for (int i = 2; i < arg.length; i++){
				description = description + arg[i] + " ";
			}
			World.getRoomByPlayer(ply).setRoomDescription(description); //Set the description of the room.
			return true;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@describe $<thing> <description>";
	}

}
