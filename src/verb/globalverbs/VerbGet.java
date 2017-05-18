package verb.globalverbs;

import gameutils.Announcement;
import items.Item;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbGet extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"get", "grab"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		
		if (args.length > 1){
			Item temp = World.getRoomByPlayer(ply).getItem(args[1]);
			if (temp != null){
				ply.getActor().addItem(World.getRoomByPlayer(ply).removeItem(temp.getName()));
				ply.sendMessageToClient("You get a " + temp.getName() + " off the ground.");
				String ann = ply.getActor().getName() + " gets a " + temp.getName() + " off the ground.";
				String[] ignored = {ply.getActor().getName()};
				Announcement.announceToRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ann, ignored);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "Get an item";
	}

}
