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
	
	/**
	 * Sets up an object with a bunch of things the script might want, or need, to access
	 * 
	 * @param player			The player calling the function if any
	 * @param caller			The thing calling this script, can be the player again
	 * @param thisArgument		The object the verb is on
	 * @param args				The string of the original argument if there is one
	 * @param directObject		The direct object if there is one
	 * @param indirectObject	The indirect object if there is one
	 * @param preposition		The preposition if there is one
	 */
	
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
