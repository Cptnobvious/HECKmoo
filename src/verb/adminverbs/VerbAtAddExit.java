package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.World;

public class VerbAtAddExit extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@add-exit", "@ae"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arguments = StringUtility.getWordListWithoutQuotes(str);
		if (arguments.length < 3){
			ply.sendMessageToClient(ColorStrings.getColoredText(true, ColorStrings.RED, ColorStrings.BLACK, "SYNTAX ERROR"));
			return false;
		}
		String exitname = arguments[1];
		String zone = arguments[2];
		int room = Integer.parseInt(arguments[3]);
		
		Exit exit = new Exit(exitname, zone, room);
		if (exit.isGoodExit()){
			World.getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom()).addExit(exit);
			String success = "Added an exit from " + ply.getActor().getLocationCode() + " to " + zone + ":" + room;
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, success));
			return true;
		}
		
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Something went wrong"));
		return false;
	}

	@Override
	public String getHelpText() {
		return "@add-exit <name> <zone> <room>";
	}

	
	
}
