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
	public boolean run(Player ply, String str) {
		String[] arg = StringUtility.getWordListWithoutQuotes(str);
		if (arg.length != 2){
			ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.RED, "SYNTAX ERROR: Bad arguments!"));
			return false;
		}
		String target = arg[1];
		if (target.equals("$here")){
			String name = "";
			for (int i = 2; i < arg.length; i++){
				name = name + arg[i] + " ";
			}
			World.getRoomByPlayer(ply).setRoomName(name);
			return true;
		}
		return false;
	}

	@Override
	public String getHelpText() {
		return "@name $here <roomname>";
	}

}
