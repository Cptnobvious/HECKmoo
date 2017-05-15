package verb.adminverbs;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import world.World;

public class VerbAtRecycle extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@recycle", "@r"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length < 3){
			//TODO add an error message here
			return false;
		} else {
			String type = args[1];
			String target = args[2];
			 int targetInt = -1;
			 try {
			 targetInt  = Integer.parseInt(args[2]);
			 } catch (NumberFormatException e){
			 //Do nothing lol
			 }
			if (type.equals("$zone")){
				World.removeZoneByID(target);
			} else if (type.equals("$room")){
				World.getZoneByID(ply.getActor().getCurrentZone()).removeRoom(targetInt);
			} else if (type.equals("$exit")){
				World.getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom()).removeExit(target);
			}
			return false;
		}
	}

	@Override
	public String getHelpText() {
		return "@recycle <type> <name>";
	}

}
