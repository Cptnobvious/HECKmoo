package verb.globalverbs;

import items.Item;
import looks.LookInventory;
import looks.LookView;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import verb.VerbDoPrepIo;
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
		VerbDoPrepIo vdpi = new VerbDoPrepIo(str);

		if (args.length == 1){
			LookView view = new LookView(ply);
			String look = view.getWhatYouSee();
			ply.sendMessageToClient(look);
			return true;
		} else {
			if (args.length > 1){
				if (!vdpi.isMalformed() && vdpi.getPreposition() != null){
					if (vdpi.getPreposition().equals("on")){
						if (vdpi.getIndirectObject().equals("floor")){
							Item item = World.getRoomByPlayer(ply).getItem(vdpi.getDirectObject());
							if (item != null){
								String look = item.getDescription();
								ply.sendMessageToClient(look);
								return true;
							}
						}
					}
					if (vdpi.getPreposition().equals("in")){

					}
				}

				if (args[1].equals("inventory") || args[1].equals("inv") || args[1].equals("i")){
					String look = LookInventory.lookAtInventory(ply);
					ply.sendMessageToClient(look);
					return true;
				} else if (ply.getActor().getItem(args[1]) != null){
					String look = LookInventory.lookThroughInventory(ply.getActor().getInventoryList(), args[1]);
					ply.sendMessageToClient(look);
					return true;
				} else if (World.getRoomByPlayer(ply).getItem(args[1]) != null){
					String look = World.getRoomByPlayer(ply).getItem(args[1]).getDescription();
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
