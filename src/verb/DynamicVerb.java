package verb;

import items.Item;
import playermanager.Player;
import script.ScriptInterface;
import script.script.HeckScript;
import script.script.ScriptArguments;

public class DynamicVerb extends Verb{
	
	protected HeckScript heckScript = null;
	protected Item parent = null;
	protected String scriptName = null;
	protected String helpText = "";
	
	public DynamicVerb(Item parent, String scriptName){
		super();
		assignScript(parent, scriptName);
	}
	
	public boolean assignScript(Item parent, String scriptName){
		this.scriptName = scriptName;
		this.heckScript = parent.getScript(scriptName);
		this.parent = parent;
		return true;
	}
	
	
	public boolean executeScript(Player ply, String str){
		heckScript = parent.getScript(scriptName);
		if (heckScript != null && heckScript.isCompiled()){
			VerbDoPrepIo vdpi = new VerbDoPrepIo(str);
			ScriptArguments args = new ScriptArguments(
					(ScriptInterface)ply.getActor(), 
					(ScriptInterface)ply.getActor(), 
					(ScriptInterface)parent, 
					str, 
					vdpi.getDirectObject(), 
					vdpi.getIndirectObject(), 
					vdpi.getPreposition());
			heckScript.execute(args);
			return true;
		}
		return false;
	}

	@Override
	public boolean run(Player ply, String str) {
		executeScript(ply, str);
		return true;
	}

	public String getScriptName(){
		return scriptName;
	}
	
	public boolean setAlias(String str){
		String[] temp = str.split(" ");
		alias = temp;
		return setAlias();
	}
	
	@Override
	public boolean setFlags() {
		return true;
	}
	
	@Override
	public boolean setAlias() {
		return true;
	}

	public boolean setHelpText(String str){
		this.helpText = str;
		return true;
	}
	
	@Override
	public String getHelpText() {
		return helpText;
	}
}
