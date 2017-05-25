package script.script;

import java.util.ArrayList;

import script.interpreter.HeckScriptInterpreter;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	private boolean compiled = false;
	private HeckScriptCompiled compiledScript = null;
	private String compilerErrorMessage = "None";
	
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
	
	public String getCompileError(){
		return this.compilerErrorMessage;
	}
	
	public boolean compile(){
		this.compilerErrorMessage = "None";
		this.compiledScript = new HeckScriptCompiled(this.lines);
		this.compiled = this.compiledScript.compile();
		if (compiled == false){
			this.compilerErrorMessage = compiledScript.getErrorMessage();
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
