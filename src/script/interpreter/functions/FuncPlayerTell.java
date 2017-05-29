package script.interpreter.functions;

import gameutils.Announcement;
import gameutils.ScriptArgumentReader;
import playermanager.Actor;
import chat.ChatMaster;
import script.attributes.Attribute;
import script.interpreter.HeckFunction;
import script.script.ScriptArguments;

public class FuncPlayerTell extends HeckFunction{

	@Override
	public boolean setFunctionName() {
		exactName = "PlayerTell";
		return true;
	}

	@Override
	public Attribute run(String[] args, ScriptArguments sa) {
		if (args.length == 0){
			return null;
		}
		
		if (sa.getPlayer() == null){
			ChatMaster.sendErrorChat("Someone tried to call FuncPlayerTell without a player");
		}
		
		String[] swapped = new String[args.length];
		
		for (int i = 0; i < swapped.length; i++){
			swapped[i] = ScriptArgumentReader.getStringFromArgumentsSafe(args[i], sa);
		}
		
		String toWork = "";
		for (int i = 0; i < swapped.length; i++){
			toWork = toWork + swapped[i] + " ";
		}
		
		Actor act = (Actor)sa.getPlayer();
		act.sendMessageToPlayer(toWork);
		
		return null;
	}

}
