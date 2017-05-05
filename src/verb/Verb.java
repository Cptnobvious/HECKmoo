package verb;

import playermanager.Player;

//The verb object, does some cool shit

public abstract class Verb {
	
	protected String[] alias = {};

	protected Verb(){
		setAlias();
	}
	
	//This function should set your alias array
	public abstract boolean setAlias();
	//This function is what happens when your verb is called
	public abstract boolean run(Player ply, String str);
	//This functions returns the help text of a verb
	public abstract String getHelpText();
	
	//This function checks to see if a given string matches an alias
	public boolean matches(String str){
		for (int i = 0; i < alias.length; i++){
			if (alias[i].equals(str)){
				return true;
			}
		}
		return false;
	}
	
	public String[] getAliases(){
		return this.alias;
	}
	
}
