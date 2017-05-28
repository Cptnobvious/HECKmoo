package gameutils;

import script.ScriptInterface;
import script.script.ScriptArguments;

public class ScriptArgumentReader {

	public static String getThingFromArgumentsSafe(String str, ScriptArguments sa){
		//Figure out which argument it is
		if (str.startsWith("player")){
			return stripPart(str, sa.getPlayer());
		} else if (str.startsWith("caller")){
			return stripPart(str, sa.getCaller());
		} else if (str.startsWith("this")){
			return stripPart(str, sa.getThisArgument());
		} else if (str.startsWith("dobj")){
			if (sa.getDirectObject() == null){
				return "TargetNotFound";
			} else {
				return sa.getDirectObject();
			}
		} else if (str.startsWith("iobj")){
			if (sa.getIndirectObject() == null){
				return "TargetNotFound";
			} else {
				return sa.getIndirectObject();
			}
		} else if (str.startsWith("prep")){
			if (sa.getPreposition() == null){
				return "TargetNotFound";
			} else {
				return sa.getPreposition();
			}
		} else {
			return str;
		}
	}
	
	
	private static String stripPart(String full, ScriptInterface target){
		if (target == null){
			
		}
		
		if (full.contains(".")){
			String[] split = full.split("\\.");
			return target.getAttribute(split[1]).sGetValue();
		} else {
			return full;
		}
	}
	
}
