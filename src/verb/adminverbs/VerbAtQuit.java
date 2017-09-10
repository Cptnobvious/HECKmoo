package verb.adminverbs;

import playermanager.Player;
import playermanager.PlayerController;
import verb.Verb;

public class VerbAtQuit extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@quit", "@disconnect"};
		alias = temp;
		return true;
	}

	@Override
	public boolean setFlags() {
		return true;
	}
	
	@Override
	public boolean run(Player ply, String str) {
		PlayerController.RemovePlayerByID(ply.getuID());
		return true;
	}

	@Override
	public String getHelpText() {
		return "When you enter this command it will save your account and disconnect you gracefully.";
	}

}
