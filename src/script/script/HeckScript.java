package script.script;

import java.util.ArrayList;

import script.interpreter.HeckScriptInterpreter;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	private boolean compiled = false;
	private HeckInstructions compiledScript = null;
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
	
	public ArrayList<String> getScriptForView(){
		return this.lines;
	}
	
	public String getCompileError(){
		return this.compilerErrorMessage;
	}
	
	public boolean compile(){
		@SuppressWarnings("unchecked")
		ArrayList<String> linesCopy = (ArrayList<String>)lines.clone();
		compiledScript = new HeckInstructions(linesCopy);
		compilerErrorMessage = "None";
		compiled = compiledScript.compile();
		if (compiled == false){
			compilerErrorMessage = compiledScript.getErrorMessage();
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
