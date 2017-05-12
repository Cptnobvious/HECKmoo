package verb.adminverbs;

import java.util.ArrayList;

import playermanager.Player;
import utility.ColorStrings;
import verb.Verb;
import world.World;
import world.Zone;

public class VerbAtAuditWorld extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@auditworld","@aw"};
		alias = temp;
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		int i = 0;
		String zones = new String();
		ArrayList<String> zonelist = World.listAllZones();
		while(i<zonelist.size()){
			zones = zones + zonelist.get(i) + "\n";
			i++;
		}
		ply.sendMessageToClient(ColorStrings.getColoredText(ColorStrings.GREEN, zones));
		return false;
	}

	@Override
	public String getHelpText() {
		
		return null;
	}

}
