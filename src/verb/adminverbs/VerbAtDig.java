package verb.adminverbs;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.Room;
import world.World;

public class VerbAtDig extends Verb{

	VerbAtDig() {
		super();
	}

	@Override
	public boolean setAlias() {
		String[] temp = {"@dig"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		//TODO: error catching
		Room rm = new Room();
		rm.setRoomName(StringUtility.getStringAfterFirst(str));
		
		int newIndex = World.getZoneByID(ply.getActor().getCurrentZone()).addRoom(rm);
		System.out.println("Player made a new room named " + rm.getRoomName());
		ply.getActor().setCurrentRoom(newIndex);
		return true;
	}

}
