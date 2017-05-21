package script.script;

import java.util.ArrayList;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	private boolean compiled = false;
	private HeckScriptCompiled compiledScript = null;
	
	public HeckScript(String name){
		this.name = name;
	}
	
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean setScript(ArrayList<String> script){
		this.lines = script;
		return true;
	}
	
	public ArrayList<String> getScript(){
		return this.lines;
	}
	
	public boolean compile(){
		compiledScript = new HeckScriptCompiled(this);
		compiled = compiledScript.compile();
		if (compiled = false){
			//Get rid of that to clear some memory since it failed anyways
			compiledScript = null;
		}
		return compiled;
	}
	
	public boolean isCompiled(){
		return compiled;
	}
	
	public boolean execute(ScriptArguments args){
		if (!isCompiled()){
			return false;
		}
		
		return true;
	}
}
