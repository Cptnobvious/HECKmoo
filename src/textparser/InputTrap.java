package textparser;

import java.util.ArrayList;

import playermanager.Player;

public abstract class InputTrap {

	protected ArrayList<String> lines = new ArrayList<String>();
	private boolean firstEmpty = false;
	protected Object[] arguments = null;
	
	public InputTrap(Object[] arguments){
		this.arguments = arguments;
	}
	
	public boolean addLine(String str){
		if (str.isEmpty() && !firstEmpty){
			firstEmpty = true;
			return true;
		} else if (str.isEmpty() && firstEmpty){
			return false;
		} else {
			lines.add(str);
			firstEmpty = false;
			return true;
		}
	}
	
	public boolean run(Player ply){
		return execute(ply);
	}
	
	public abstract boolean execute(Player ply);
	
}
