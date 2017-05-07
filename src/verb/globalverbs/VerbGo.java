package verb.globalverbs;

import playermanager.Player;
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
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		Room rm = World.getRoomByPlayer(ply);
		Exit ex = rm.getExitByName(arg[1]);
		if (ex == null){
			return false;
		}
		ply.getActor().setCurrentRoom(ex.getZone(), ex.getRoom());
		//TODO: a better system than simulating a look command6
		ply.sendMessageToLogic("look");
		return true;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
