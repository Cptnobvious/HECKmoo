package script.script;

import java.util.ArrayList;

public class ScriptList {

	private ArrayList<HeckScript> scripts = new ArrayList<HeckScript>();
	
	public boolean addScript(HeckScript script){
		if (!scriptExists(script.getName())){
			scripts.add(script);
			return true;
		} 
		return false;
	}
	
	public boolean removeScript(String name){
		if (scriptExists(name)){
			for (int i = 0; i < scripts.size(); i++){
				if (scripts.get(i).getName().equalsIgnoreCase(name)){
					scripts.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public HeckScript getScript(String name){
		for (int i = 0; i < scripts.size(); i++){
			if (scripts.get(i).getName().equals(name)){
				return scripts.get(i);
			}
		}
		return null;
	}
	
	public boolean scriptExists(String name){
		for (int i = 0; i < scripts.size(); i++){
			if (scripts.get(i).getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getScriptNames(){
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < scripts.size(); i++){
			names.add(scripts.get(i).getName());
		}
		return names;
	}
	
}
