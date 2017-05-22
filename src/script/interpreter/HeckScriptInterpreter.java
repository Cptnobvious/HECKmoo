package script.interpreter;

import java.util.ArrayList;

import script.attributes.Attribute;
import script.script.HeckScriptCompiled;
import script.script.ScriptArguments;
import utility.StringUtility;

public class HeckScriptInterpreter {
	
	private static Attribute returnRegister = null;
	
	//Should try to execute the script and catch errors
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
		String[] lineParts = StringUtility.getWordList(line);
		//What is this line doing
		String intention = lineParts[0];
		
		//Moving one value to another?
		if (intention.equals("mov")){
			
			//Do you have enough variables?
			if (lineParts.length > 2){
				getThing(lineParts[1], sa).sSetValue(lineParts[2]);
				return true;
			} else {
				return false;
			}
			
		} else if (intention.equals("func")){
			//Make sure you have enough arguments
			
			if (lineParts.length >= 2){
				//Pull the function from the list
				HeckFunction func= HeckFunctionList.getFunction(lineParts[1]);
				
				if (func != null){
					//Set up the arguments list if there is one
					String[] argumentList = new String[lineParts.length - 2];
					for(int i = 0; i < argumentList.length; i++){
						argumentList[i] = lineParts[i + 2];
					}
					
					//Run the function and set the return register
					
					func.run(argumentList, sa);
					
					return true;
				}
				
				return false;
			} else {
				return false;
			}
			
		}
		
		return true;
	}
	
	//Returns either the proper thing or a piece of junk to be a target dummy
	private static Attribute getThing(String s, ScriptArguments sa){
		
		//Check for the this argument
		if (s.startsWith("this")){
			
			//Is it talking about an attribute?
			if (s.contains(".")){
				//If it does, get the attribute name then
				String[] attributeSplit = s.split("\\.");
				return sa.getThisArgument().getAttribute(attributeSplit[1]);
			}
			
		}
		
		return new Attribute("dummy");
	}
}
