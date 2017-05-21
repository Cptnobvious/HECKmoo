package script;

import java.util.ArrayList;

import script.attributes.Attribute;
import script.script.HeckScript;

public interface ScriptInterface {
	
	//Attribute stuff
	public abstract boolean 	setAttribute(String name, int i);
	public abstract boolean 	setAttribute(String name, double d);
	public abstract boolean 	setAttribute(String name, String str);
	public abstract boolean 	setAttribute(String name, boolean b);
	public abstract Attribute	getAttribute(String name);
	public abstract ArrayList<Attribute> getAttributeList();
	
	//Scripts stuff
	public abstract boolean 	addScript(HeckScript script);
	public abstract boolean 	removeScript(String name);
	public abstract HeckScript 	getScript(String name);
	public abstract boolean 	scriptExists(String name);
	public abstract boolean		isScriptCompiled(String name);
	public abstract ArrayList<String> getScriptNames();

}
