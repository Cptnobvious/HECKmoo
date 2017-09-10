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
	public boolean setFlags() {
		String[] temp = {"builder", "admin"};
		flags = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arguments = StringUtility.getWordListWithoutQuotes(str);
		if (arguments.length != 4){
			ply.sendMessageToClient(ColorStrings.getColoredText(true, ColorStrings.RED, ColorStrings.BLACK, "SYNTAX ERROR"));
			//Prevent too few arguments.
			return false;
		}
		String exitname = arguments[1]; //Get the name of the exit.
		String zone = arguments[2]; //Get the zone.
		int room = Integer.parseInt(arguments[3]); //Get the room the exit is leading to.
		if (World.getRoomByPlayer(ply).getExitByExactName(exitname) != null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Exit already exists!"));
			//Prevent duplicate exits.
			return false;
		}
		
		Exit exit = new Exit(exitname, zone, room); //Make the new exit.
		if (exit.isGoodExit()){
			World.getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom()).addExit(exit);
			String success = "Added an exit from " + ply.getActor().getLocationCode() + " to " + zone + ":" + room;
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, success));
			return true;
		}
		
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Exit creation failed. (Invalid room and/or zone.)"));
		return false;
	}

	@Override
	public String getHelpText() {
		return "@add-exit <name> <zone> <room>";
	}
	
	
}
