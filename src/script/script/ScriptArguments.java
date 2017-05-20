package script.script;

import script.ScriptInterface;

public class ScriptArguments {
	
	//Thing that called the object
	ScriptInterface player = null;
	//Thing that called the object
	ScriptInterface caller = null;
	//The object the verb was on
	ScriptInterface thisArgument = null;
	//String that they sent the script, if any
	String args = null;
	//The direct object, if there is one
	String directObject = null;
	//The indirect object if there is one
	String indirectObject = null;
	//The preposition
	String preposition = null;
	
	public ScriptArguments(ScriptInterface player, ScriptInterface caller, ScriptInterface thisArgument, String args, String directObject, String indirectObject, String preposition){
		this.player 		= player;
		this.caller 		= caller;
		this.thisArgument 	= thisArgument;
		this.args 			= args;
		this.directObject 	= directObject;
		this.indirectObject = indirectObject;
		this.preposition 	= preposition;
	}

	public ScriptInterface getPlayer() {
		return player;
	}

	public ScriptInterface getCaller() {
		return caller;
	}

	public ScriptInterface getThisArgument() {
		return thisArgument;
	}

	public String getArgs() {
		return args;
	}

	public String getDirectObject() {
		return directObject;
	}

	public String getIndirectObject() {
		return indirectObject;
	}

	public String getPreposition() {
		return preposition;
	}
	
	

}
