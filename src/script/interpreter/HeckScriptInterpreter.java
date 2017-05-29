package script.interpreter;

import gameutils.ScriptArgumentReader;

import java.util.ArrayList;

import script.attributes.Attribute;
import script.script.HeckInstructions;
import script.script.ScriptArguments;
import utility.StringUtility;

public class HeckScriptInterpreter {
	
	//Should try to execute the script and catch errors
	public static boolean execute(HeckInstructions hsc, ScriptArguments sa){
		
		ArrayList<String> toRun = hsc.getCompiledScript();
		if (toRun == null){
			return false;
		}
		
		int nextLine = 0;
		String line = null;
		
		while (nextLine < toRun.size()){
			line = toRun.get(nextLine);
			//Check the conditional or interpret a block
			if (HeckInstructions.lineHasConditional(line) && !line.startsWith("BLOCKINDEX")){
				nextLine = interpretConditional(hsc, sa, nextLine, line);
				//Break from the loop if the conditional did something strange
				if (nextLine == -1){
					break;
				}
			} else {
				interpretBlock(hsc, sa, line);
				//go to the end of the conditional you're in if you're in one
				//First look above yourself
				if (nextLine > 0 && HeckInstructions.lineHasConditional(toRun.get(nextLine - 1))){
					//if that's an if, elseif, or else, just get out
					String last = toRun.get(nextLine - 1);
					if (last.startsWith("if") || last.startsWith("elseif") || last.startsWith("else")){
						//run until you find the endif
						for (int i = nextLine; i < toRun.size(); i++){
							if (toRun.get(i).startsWith("endif")){
								nextLine = i;
							}
						}
					}
				} 
				
				nextLine++;
			}
		}
		
		return true;
	}

	
	private static int interpretConditional(HeckInstructions hsc, ScriptArguments sa, int onLine, String line){
		
		ArrayList<String> toRun = hsc.getCompiledScript();
		
		//Check what we're dealing with and make sure it isn't malformed
		if (line.startsWith("if")){
			//Figure out the if statement
			String[] args = StringUtility.getWordList(line);
			
			if (args.length > 4){
				return -1;
			}
			
			//So the arguments are
			//0 - if
			//1 - first variable
			//2 - condition (= < > <= >= != and !!)
			//3 - second variable
			
			//Make sure you can find both variables and find them now
			String var1String = null;
			String var2String = null;
			
			var1String = ScriptArgumentReader.getStringFromArgumentsSafe(args[1], sa);
			var2String = ScriptArgumentReader.getStringFromArgumentsSafe(args[3], sa);
			
			//Initialize attributes from these
			Attribute att1 = new Attribute("1", var1String);
			Attribute att2 = new Attribute("2", var2String);
			
			//Now figure out which case we use
			//The cases are
			//1 - =
			//2 - !=
			//3 - <
			//4 - >
			//5 - <=
			//6 - >=
			//7 - !!
			String con = args[2];
			boolean condtrue = false;
			if (con.equals("=")){
				condtrue = att1.equals(att2);
			} else if (con.equals("!=")){
				condtrue = att1.notEquals(att2);
			} else if (con.equals("<")){
				condtrue = att1.lessThan(att2);
			} else if (con.equals(">")){
				condtrue = att1.greaterThan(att2);
			} else if (con.equals("<=")){
				condtrue = att1.lessThanEqual(att2);
			} else if (con.equals(">=")){
				condtrue = att1.greaterThanEqual(att2);
			} else if (con.equals("!!")){
				condtrue = true;
			}
			
			//If it was true just run the next block
			if (condtrue){
				return (onLine + 1);
			}
			
			//Otherwise go hunt for an elseif or else statement, giveup if we find the endif
			for (int i = onLine; i < toRun.size(); i++){
				if (toRun.get(i).startsWith("elseif")){
					return i;
				} else if (toRun.get(i).startsWith("else")){
					return i;
				} else if (toRun.get(i).startsWith("endif")){
					return i;
				}
			}
			
			
		} else if (line.startsWith("elseif")){
			//Figure out the if statement
			String[] args = StringUtility.getWordList(line);
			
			if (args.length > 4){
				return -1;
			}
			
			//So the arguments are
			//0 - if
			//1 - first variable
			//2 - condition (= < > <= >= != and !!)
			//3 - second variable
			
			//Make sure you can find both variables and find them now
			String var1String = null;
			String var2String = null;
			
			var1String = ScriptArgumentReader.getStringFromArgumentsSafe(args[1], sa);
			var2String = ScriptArgumentReader.getStringFromArgumentsSafe(args[3], sa);
			
			//Initialize attributes from these
			Attribute att1 = new Attribute("1", var1String);
			Attribute att2 = new Attribute("2", var2String);
			
			//Now figure out which case we use
			//The cases are
			//1 - =
			//2 - !=
			//3 - <
			//4 - >
			//5 - <=
			//6 - >=
			//7 - !!
			String con = args[2];
			boolean condtrue = false;
			if (con.equals("=")){
				condtrue = att1.equals(att2);
			} else if (con.equals("!=")){
				condtrue = att1.notEquals(att2);
			} else if (con.equals("<")){
				condtrue = att1.lessThan(att2);
			} else if (con.equals(">")){
				condtrue = att1.greaterThan(att2);
			} else if (con.equals("<=")){
				condtrue = att1.lessThanEqual(att2);
			} else if (con.equals(">=")){
				condtrue = att1.greaterThanEqual(att2);
			} else if (con.equals("!!")){
				condtrue = true;
			}
			
			//If it was true just run the next block
			if (condtrue){
				return (onLine + 1);
			}
			
			//Otherwise go hunt for an elseif or else statement, giveup if we find the endif
			for (int i = onLine; i < toRun.size(); i++){
				if (line.startsWith("elseif")){
					return i;
				} else if (line.startsWith("else")){
					return i;
				} else if (line.startsWith("endif")){
					return i;
				}
			}
			
		} else if (line.startsWith("else")){
			//If you sent things after this else just end the script
			if (StringUtility.getWordList(line).length > 1){
				return -1;
			} else { //Otherwise continue it
				if (onLine + 1 < toRun.size()){
					return onLine + 1;
				}
			}
			
		} else if (line.startsWith("endif")){
			return onLine + 1;
		}
		
		return -1;
	}
	
	
	private static boolean interpretBlock(HeckInstructions hsc, ScriptArguments sa, String str){
		String[] args = StringUtility.getWordList(str);
		ArrayList<String> block = hsc.getBlock(Integer.parseInt(args[1]));
		for (int i = 0; i < block.size(); i++){
			interpretLine(block.get(i), sa);
		}
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
				String second = getThing(lineParts[2], sa).sGetValue();
				getThing(lineParts[1], sa).sSetValue(second);
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
		
		//check for the indirect object
		if (s.startsWith("IObj")){
			if (sa.getIndirectObject() != null){
				return new Attribute(null, sa.getIndirectObject());
			}
		}
		
		return new Attribute(null, s);
	}
}
