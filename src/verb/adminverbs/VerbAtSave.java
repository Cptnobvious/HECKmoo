package verb.adminverbs;

import playermanager.Player;
import saving.SaveManager;
import verb.Verb;

public class VerbAtSave extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@save"};
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
		SaveManager.saveAll();
		return true;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return "@save";
	}

}
