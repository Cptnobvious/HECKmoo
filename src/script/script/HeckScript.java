package script.script;

import java.util.ArrayList;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	private boolean compiled = false;
	
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
		compiled = true;
		return true;
	}
	
	public boolean isCompiled(){
		return compiled;
	}

}
