package verb.adminverbs;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.Exit;
import world.Room;
import world.World;

public class VerbAtTeleport extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@teleport","@tele"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		
		return true;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
