package script.interpreter.functions;

import gameutils.Announcement;
import playermanager.Actor;
import script.attributes.Attribute;
import script.interpreter.HeckFunction;
import script.script.ScriptArguments;
import world.World;

public class FuncPlayerAnnounceRoom extends HeckFunction{

	//Announces exact text to the room a player is in
	
	@Override
	public boolean setFunctionName() {
		exactName = "AnnouncePlayerRoom";
		return true;
	}

	@Override
	public Attribute run(String[] args, ScriptArguments sa){
		
		if (sa.getPlayer() != null){
			String ann = "";
			for (int i = 0; i < args.length; i++){
				ann = ann + args[i] + " ";
			}
			Announcement.announceToRoomRaw(sa.getPlayer().getAttribute("_ZONE").sGetValue(), sa.getPlayer().getAttribute("_ROOM").iGetValue(), ann);
		}
		
		return null;
	}

}
