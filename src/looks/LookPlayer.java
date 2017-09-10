package looks;

import playermanager.Player;
import utility.ColorStrings;

public class LookPlayer {
	
	public static String lookAtAPlayer(Player ply){
		String look = "";
		look = look + ColorStrings.getBoldText(ply.getActor().getName()) + "\n";
		look = look + ply.getActor().getDescription();
		return look;
	}

}
