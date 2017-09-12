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
	public boolean setFlags() {
		String[] temp = {"builder", "admin"};
		flags = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length != 8){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String target = arg[1];
		if (target.equals("$here")){
				int x = Integer.parseInt(arg[2]);
				int y = Integer.parseInt(arg[3]);
				int fColor = ColorStrings.getColorIntByString(arg[4]);
				int bColor = ColorStrings.getColorIntByString(arg[5]);
				boolean bold = arg[6].equals("true");
				String symbol = arg[7];
				
				World.getZoneByPlayer(ply).getMap().setMapPoint(x, y, fColor, bColor, bold, symbol);
				World.getRoomByPlayer(ply).setMapPos(x, y);
				return true;
			}
		try {
			int index = Integer.parseInt(arg[1]);
			if (World.getZoneByPlayer(ply).getRoomByIndex(index) != null){
				int x = Integer.parseInt(arg[2]);
				int y = Integer.parseInt(arg[3]);
				int fColor = ColorStrings.getColorIntByString(arg[4]);
				int bColor = ColorStrings.getColorIntByString(arg[5]);
				boolean bold = arg[6].equals("true");
				String symbol = arg[7];
				
				World.getZoneByPlayer(ply).getMap().setMapPoint(x, y, fColor, bColor, bold, symbol);
				World.getZoneByPlayer(ply).getRoomByIndex(index).setMapPos(x, y);
				return true;
			}
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Bad index!"));
			return false;
		}
		catch (NumberFormatException e) {
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid target!"));
	        return false;
	    }

	}

	@Override
	public String getHelpText() {
		return "@edit-minimap <room-index> <x-coord> <y-coord> <fColor> <bColor> <bold> <symbol>\n"
				+ "Can use $here for where you're standing instead of an index number.";
	}

}
