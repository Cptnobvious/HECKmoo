package verb.globalverbs;

import playermanager.Player;
import verb.Verb;

public class VerbGo extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"go", "g"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
