package verb.globalverbs;

import gameutils.Announcement;
import items.Item;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbDrop extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"drop", "d"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		
		if (args.length > 1) {
			Item temp = ply.getActor().getItem(args[1]);
			if (temp != null){
				World.getRoomByPlayer(ply).addItem(ply.getActor().removeItem(temp.getName()));
				ply.sendMessageToClient("You drop a " + temp.getName() + " on the ground.");
				String ann = (ply.getActor().getName() + " drops a " + temp.getName() + " on the ground.");
				String[] ignored = {ply.getActor().getName()};
				Announcement.announceToRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ann, ignored);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "Drop this item";
	}

}
