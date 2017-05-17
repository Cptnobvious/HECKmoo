package verb.globalverbs;

import looks.LookInventory;
import looks.LookView;
import playermanager.Player;
import utility.StringUtility;
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
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length == 1){
			LookView view = new LookView(ply);
			String look = view.getWhatYouSee();
			ply.sendMessageToClient(look);
			return true;
		} else {
			if (args.length > 1){
				if (args[1].equals("inventory") || args[1].equals("inv") || args[1].equals("i")){
					String look = LookInventory.lookAtInventory(ply);
					ply.sendMessageToClient(look);
					return true;
				} else {
					String look = LookInventory.lookThroughInventory(ply.getActor().getInventoryList(), args[1]);
					ply.sendMessageToClient(look);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "look <thing>";
	}
	
	

}
