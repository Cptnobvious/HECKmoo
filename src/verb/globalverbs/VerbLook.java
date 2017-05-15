package verb.globalverbs;

import gameutils.LookView;
import playermanager.Player;
import verb.Verb;
import world.World;

public class VerbLook extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"look", "l"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		//TODO: look at yourself
		//TODO: look at object
		//String look = World.getRoomLook(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom());
		LookView view = new LookView(ply);
		String look = view.getWhatYouSee();
		ply.sendMessageToClient(look);
		return false;
	}

	@Override
	public String getHelpText() {
		return "look <thing>";
	}
	
	

}
