package verb.adminverbs;

import com.Boot;

import playermanager.Player;
import verb.Verb;

public class VerbAtShutdown extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@shutdown"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		Boot.requestShutdown();
		return true;
	}

	@Override
	public String getHelpText() {
		return "@shutdown\nThis turns off the server so don't abuse it";
	}

}
