package script.script;

import java.util.ArrayList;

import script.interpreter.HeckScriptInterpreter;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	private boolean compiled = false;
	private HeckInstructions compiledScript = null;
	
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
		this.compiledScript = new HeckInstructions(this.lines);
		this.compiled = this.compiledScript.compile();
		if (compiled == false){
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
		
		HeckScriptInterpreter.execute(compiledScript, args);
		
		return true;
	}
}
