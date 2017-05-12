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
		if (arg.length < 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String target = arg[1];
		if (target.equals("$here")){
			if (arg.length == 8){
				int x = Integer.parseInt(arg[2]);
				int y = Integer.parseInt(arg[3]);
				int fColor = ColorStrings.getColorIntByString(arg[4]);
				int bColor = ColorStrings.getColorIntByString(arg[5]);
				boolean bold = arg[6].equals("true");
				String symbol = ColorStrings.replaceTags(arg[7]);
				
				World.getZoneByPlayer(ply).getMap().setMapPoint(x, y, fColor, bColor, bold, symbol);
				World.getRoomByPlayer(ply).setMapPos(x, y);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@edit-minimap <room-index> <x-coord> <y-coord> <fColor> <bColor> <bold> <symbol>\n"
				+ "Can use $here for where you're standing instead of an index number.";
	}

}
