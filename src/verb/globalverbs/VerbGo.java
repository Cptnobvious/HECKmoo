package verb.globalverbs;

import gameutils.Announcement;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.Room;
import world.World;

public class VerbGo extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"go", "g"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str); //Get arguments.
		Room rm = World.getRoomByPlayer(ply); //Get the player's room.
		if (arg.length != 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Go where?"));
			return false;
		}
		Exit ex = rm.getExitByName(arg[1]); //Find the exit from the second argument.
		if (ex == null){
			return false; //Prevent going to an exit that doesn't exist.
		}
		ply.getActor().setCurrentRoom(ex.getZone(), ex.getRoom()); //Set the player's room.
		//TODO: a better system than simulating a look command6
		ply.sendMessageToLogic("look");
		
		//let everyone know you showed up
		Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ply.getActor().getName() + " arrives.");
		return true;
	}

	@Override
	public String getHelpText() {
		return "go <direction>";
	}

}
