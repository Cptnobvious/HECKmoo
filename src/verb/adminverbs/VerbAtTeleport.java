package verb.adminverbs;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.Room;
import world.World;
import world.Zone;

public class VerbAtTeleport extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@teleport","@tele"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		Zone zn = World.getZoneByID(arg[1]);
		 int Room = Integer.parseInt(arg[2]);
		Room rm = World.getRoom(zn.getZoneID(), Room);
		if (rm == null){
			return false;
		}
		ply.getActor().setCurrentRoom(zn.getZoneID(), rm.getIndex());
		ply.sendMessageToLogic("look");
		return true;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
