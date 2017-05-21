package script.script;

import java.util.ArrayList;

public class HeckScriptInterpreter {
	
	//Shoulds try to execute the script and catch errors
	public static boolean execute(HeckScriptCompiled hsc, ScriptArguments sa){
		
		int iBlock = 0;
		
		do {
			ArrayList<String> currentTask = hsc.getBlock(iBlock);
			
			for (int i = 0; i < currentTask.size(); i++){
				interpretLine(currentTask.get(i), sa);
			}
			
		} while (false);
		
		return true;
	}

	
	private static boolean interpretLine(String line, ScriptArguments sa){
		//Find what object, if any, it's talking about
		
		//Is it talking about the object the script is on?
		if (line.startsWith("this")){
			//Check to see if it's talking about an attribute
			if (line.contains(".")){
				//If it does, get the attribute name then
				String[] attributeSplit = line.split("\\.");
				//Split by spaces then, since it should be attrib = newval
				String[] splitAtEquals = attributeSplit[1].split(" ");
				
				sa.getThisArgument().setAttribute(splitAtEquals[0], splitAtEquals[2]);
			}
		}
		
		return true;
	}
}
