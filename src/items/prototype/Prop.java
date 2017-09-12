package items.prototype;

import java.util.ArrayList;

import script.script.HeckScript;
import verb.Verb;
import items.Item;

public class Prop extends Item{

	String parentName = null;
	
	Prop(String name){
		super(PrototypeList.getPrototypeByName(name));
	}
	
	private Prototype getParent(){
		return PrototypeList.getPrototypeByName(parentName);
	}
	
	@Override
	public ArrayList<String> sGetVerbList(){
		return getParent().sGetVerbList();
	}
	
	@Override
	public Verb getVerb(String verb){
		return getParent().getVerb(verb);
	}
	
	@Override
	public HeckScript getScript(String name) {
		return getParent().getScript(name);
	}

	@Override
	public boolean scriptExists(String name) {
		return getParent().scriptExists(name);
	}

	@Override
	public boolean isScriptCompiled(String name) {
		return getParent().isScriptCompiled(name);
	}
	
	@Override
	public ArrayList<String> getScriptNames() {
		return getParent().getScriptNames();
	}
	
}
