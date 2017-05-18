package items.items;

import playermanager.Player;
import verb.Verb;
import verb.globalverbs.VerbDrop;
import gameutils.Announcement;
import items.Item;

public class Rock extends Item{
	
	public Rock(){
		super();
		name = "Rock";
		plural = "Rocks";
		description = "It is a rock.";
		addVerb(new Pet());
	}
	
	
	private class Pet extends Verb{

		@Override
		public boolean setAlias() {
			String[] temp = {"pet"};
			alias = temp;
			return true;
		}

		@Override
		public boolean run(Player ply, String str) {
			if (invokeItem(ply, str) == null){
				return false;
			} else {
				Announcement.announceToRoomRaw(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom(), ply.getActor().getName() + " pets their " + invokeItem(ply, str).getName() + ".");
			}
			return false;
		}

		@Override
		public String getHelpText() {
			return "Pet the rock";
		}
		
	}
}
