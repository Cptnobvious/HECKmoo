package verb.adminverbs;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbAtName extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@name" , "@rename"};
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
		if (arg.length != 3){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String target = arg[1];
		if (target == null){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "Invalid target!"));
			return false;
		}
		if (target.equals("$here")){ //Targeting the room the player is in.
			String name = "";
			for (int i = 2; i < arg.length; i++){
				name = name + arg[i] + " ";
			}
			World.getRoomByPlayer(ply).setRoomName(name); //Name the room.
			return true;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@name $here <roomname>";
	}

}
