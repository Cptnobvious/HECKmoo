package script.interpreter.functions;

import playermanager.Actor;
import chat.ChatMaster;
import gameutils.Announcement;
import gameutils.ScriptArgumentReader;
import script.attributes.Attribute;
import script.interpreter.HeckFunction;
import script.script.ScriptArguments;

//Tells the room something with proper replacements

public class FuncPlayerRoomTell extends HeckFunction{

	@Override
	public boolean setFunctionName() {
		exactName = "PlayerRoomTell";
		return true;
	}

	@Override
	public Attribute run(String[] args, ScriptArguments sa) {
		if (args.length == 0){
			return null;
		}
		
		if (sa.getPlayer() == null){
			ChatMaster.sendErrorChat("Someone tried to call FuncPlayerRoomTell without a player");
		}
		
		String[] swapped = new String[args.length];
		
		for (int i = 0; i < swapped.length; i++){
			swapped[i] = ScriptArgumentReader.getStringFromArgumentsSafe(args[i], sa);
		}
		
		String toWork = "";
		for (int i = 0; i < swapped.length; i++){
			toWork = toWork + swapped[i] + " ";
		}
		
		
		Actor temp = (Actor)sa.getPlayer();
		
		String[] ignoreMe = {temp.getName()};
		
		Announcement.announceToRoom(temp.getCurrentZone(), temp.getCurrentRoom(), toWork, ignoreMe);
		
		return null;
	}

}
