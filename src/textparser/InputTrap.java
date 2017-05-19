package textparser;

import java.util.ArrayList;

import playermanager.Player;

public abstract class InputTrap {

	private ArrayList<String> lines = new ArrayList<String>();
	private boolean firstEmpty = false;
	
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
