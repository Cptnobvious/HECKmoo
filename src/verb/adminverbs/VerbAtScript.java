package verb.adminverbs;

import playermanager.Player;
import script.ScriptInterface;
import script.script.HeckScript;
import textparser.InputTrap;
import utility.StringUtility;
import verb.Verb;

public class VerbAtScript extends Verb{

	@Override
	public boolean setAlias() {
		String[] temp = {"@script"};
		alias = temp;
		return true;
	}

	@Override
	public boolean run(Player ply, String str) {
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length > 3){
			return false;
		}
		
		//Setup the target and attribute to edit
		//First count the :'s so we don't fuck things up
		String target = null;
		String scriptName = null;
		
		int colons = StringUtility.countCharInString(args[1], ':');
		if (colons == 0){
			return false;
		} else if (colons == 1){
			
			String toBreak = args[1];
			String[] broken = toBreak.split(":");
			if (broken.length != 2){
				return false;
			}
			target = broken[0];
			scriptName = broken[1];
		}
		
		if (args.length == 2){
			//Find the thing and set the value
			if (ply.getActor().getItem(target) != null){
				Object[] arguments = {scriptName, ply.getActor().getItem(target)};
				ply.startInputTrap(new VerbScriptTrap(arguments));
				return true;
			}
		} else if (args.length == 3){
			if (args[2].equals("compile")){
				ply.getActor().getItem(target).getScript(scriptName).compile();
				return true;
			}
		}
		return false;
	}

	private class VerbScriptTrap extends InputTrap{
		
		public VerbScriptTrap(Object[] arguments){
			super(arguments);
		}
		
		@Override
		public boolean execute(Player ply) {
			if (arguments.length != 2){
				return false;
			}
			if (arguments[0] instanceof String){
				if (arguments[1] instanceof ScriptInterface){
					HeckScript script = new HeckScript((String)arguments[0]);
					script.setScript(lines);
					((ScriptInterface)arguments[1]).addScript(script);
					return true;
				}
			}
			
			
			return false;
		}
		
	}
	
	@Override
	public String getHelpText() {
		return "@script <target>:<scriptname>";
	}

}
