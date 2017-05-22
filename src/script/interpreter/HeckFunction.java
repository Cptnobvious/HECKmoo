package script.interpreter;

import script.attributes.Attribute;
import script.script.ScriptArguments;

public abstract class HeckFunction {
	
	protected String exactName = "null";
	
	public abstract boolean setFunctionName();
	public abstract Attribute run(String[] args, ScriptArguments sa);
	
	public boolean checkName(String str){
		return str.equals(exactName);
	}

	public String getName(){
		return exactName;
	}
}
