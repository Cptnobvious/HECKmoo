package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.World;
import world.Zone;

public class VerbAtAddExitBi extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@add-exitbi", "@aebi"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arguments = StringUtility.getWordListWithoutQuotes(str);
		if (arguments.length != 5){
			ply.sendMessageToClient(ColorStrings.getColoredText(true, ColorStrings.RED, ColorStrings.BLACK, "SYNTAX ERROR: Bad arguments!"));
			//Prevent too few arguments.
			return false;
		}
		String exitname = arguments[1]; //Get the name of the exit.
		String exitbackname = arguments[2];
		String zone = arguments[3]; //Get the zone.
		Zone destzone = World.getZoneByID(arguments[3]);
		if (destzone == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad zone!"));
			return false;
		}
		int room = Integer.parseInt(arguments[4]); //Get the room the exit is leading to/from.
		if (destzone.getRoomByIndex(room) == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad room index!"));
			return false;
		}
		if (World.getRoomByPlayer(ply).getExitByExactName(exitname) != null || destzone.getRoomByIndex(room).getExitByExactName(exitbackname) != null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Exit already exists!"));
			//Prevent duplicate exits.
			return false;
		}
		
		Exit exit = new Exit(exitname, ply.getActor().getCurrentZone(), room); //Make the new exit.
		Exit exitback = new Exit(exitbackname, zone, ply.getActor().getCurrentRoom()); //Make the new exit.
		if (exit.isGoodExit() && exitback.isGoodExit()){
			World.getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom()).addExit(exit);
			World.getRoom(zone, room).addExit(exitback);
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN,
					"Created new bi-directional exits: " + exitname + ", and " + exitbackname + "."));  //Wrapped to be readable.
			return true;
		}
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Exit creation failed!"));
		return false;
	}

	@Override
	public String getHelpText() {
		return "@add-exitbi <name> <name> <zone> <room>";
	}

	
	
}
