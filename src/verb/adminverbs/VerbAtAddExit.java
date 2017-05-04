package verb.adminverbs;

import playermanager.Player;
import verb.Verb;

public class VerbAtAddExit extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@add-exit", "@ae"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		//Format: @add-exit <name>, <zone>, <room>
		
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
