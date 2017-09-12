package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.Map;
import world.Room;
import world.World;

public class VerbAtDigDir extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@digdir"};
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
		Room rm = new Room();
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length != 3){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String roomname = arg[1]; //Find the room name.
		if (roomname == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "You need to enter a room name!"));
			return false;
		}
		rm.setRoomName(roomname);
		int newIndex = World.getZoneByID(ply.getActor().getCurrentZone()).addRoom(rm); //Add a room to the player's current zone.
		Room plyroom = World.getRoomByPlayer(ply);
		int xoffset = 0; int yoffset = 0;
		boolean error = true;
		String exitonename = null;
		String exittwoname = null;
		if(arg[2].equals("west")){
			xoffset = -1;
			exitonename = "west";
			exittwoname = "east";
			error = false;
		}
		if(arg[2].equals("east")){
			xoffset = 1;
			exitonename = "east";
			exittwoname = "west";
			error = false;
		}
		if(arg[2].equals("north")){
			yoffset = -1;
			exitonename = "north";
			exittwoname = "south";
			error = false;
		}
		if(arg[2].equals("south")){
			xoffset = 1;
			exitonename = "south";
			exittwoname = "north";
			error = false;
		}
		if(error == true){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "ERROR: Bad direction!"));
			return false;
		}
		if(plyroom.getMapX()+xoffset<0 || plyroom.getMapX()+xoffset>Map.MAPW){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "ERROR: Room would be out of bounds!"));
			return false;
		}
		if(plyroom.getMapY()+yoffset<0 || plyroom.getMapY()+yoffset>Map.MAPH){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "ERROR: Room would be out of bounds!"));
			return false;
		}
		rm.setMapPos(plyroom.getMapX()+xoffset, plyroom.getMapY()+yoffset);
		World.getZoneByPlayer(ply).getMap().setMapPoint(plyroom.getMapX()+xoffset, plyroom.getMapY()+yoffset, ColorStrings.BLACK, ColorStrings.WHITE, false, "..");
		System.out.println("Player made a new room named " + rm.getRoomName());
		
		Exit exit = new Exit(exitonename, ply.getActor().getCurrentZone(), newIndex); //Make the new exit.
		if (exit.isGoodExit()){
			World.getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom()).addExit(exit);
		}
		
		Exit exitback = new Exit(exittwoname, ply.getActor().getCurrentZone(), plyroom.getIndex()); //Make the new exit.
		if (exitback.isGoodExit()){
			World.getRoom(ply.getActor().getCurrentZone(), newIndex).addExit(exitback);
		}
		
		ply.getActor().setCurrentRoom(newIndex); //Put the player in the new room.
		//TODO: better internal calls
		ply.sendMessageToLogic("look");
		return true;
	}

	@Override
	public String getHelpText() {
		return "@digdir <roomname> <north/east/south/west>";
	}

}
