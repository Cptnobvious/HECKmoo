package verb.adminverbs;

import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbAtEditMinimap extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@edit-minimap"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		String target = arg[1];
		if (target.equals("$here")){
			if (arg.length == 5){
				int x = Integer.parseInt(arg[2]);
				int y = Integer.parseInt(arg[3]);
				String symbol = ColorStrings.replaceTags(arg[4]);
				World.getZoneByPlayer(ply).getMap().setMapPoint(x, y, symbol);
				World.getRoomByPlayer(ply).setMapPos(x, y);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@edit-minimap <room-index> <x-coord> <y-coord> <symbol>\n"
				+ "Can use $here for where you're standing instead of an index number.";
	}

}
