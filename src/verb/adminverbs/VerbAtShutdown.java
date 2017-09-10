package verb.adminverbs;

import com.Boot;

import playermanager.Player;
import textparser.InputTrap;
import utility.ColorStrings;
import verb.Verb;

public class VerbAtShutdown extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@shutdown"};
		alias = temp;
		return true;
	}
	
	@Override
	public boolean setFlags() {
		String[] temp = {"admin"};
		flags = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		sendFeedback(ply, ColorStrings.RED, "Are you sure you want to shut down? yes/no");
		ply.startInputTrap(new ShutdownTrap(null));
		return true;
	}

	class ShutdownTrap extends InputTrap{

		public ShutdownTrap(Object[] arguments) {
			super(arguments);
		}

		@Override
		public boolean execute(Player ply) {
			if (lines.get(0).equals("yes")){
				ply.sendMessageToClient("Shutting down");
				Boot.requestShutdown();
				return true;
			} else {
				ply.sendMessageToClient("Okay, nevermind");
			}
			return false;
		}
		
	}
	
	@Override
	public String getHelpText() {
		return "@shutdown\nThis turns off the server so don't abuse it";
	}

}
