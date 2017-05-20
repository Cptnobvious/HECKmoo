package script.script;

import java.util.ArrayList;

public class HeckScript {
	
	private String name = null;
	private ArrayList<String> lines = new ArrayList<String>();
	
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

}
