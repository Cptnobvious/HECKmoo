package verb;

import items.Item;
import playermanager.Player;
import utility.ColorStrings;
import utility.StringUtility;

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
	
	//Pulls access to an item so you can do stuff to it
	public Item invokeItem(Player ply, String str){
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		if (args.length > 1){
			return ply.getActor().getItem(args[1]);
		}
		return null;
	}
	
	protected boolean sendFeedback(Player ply, int color, String str){
		return sendFeedback(ply, color, false, str);
	}
	
	protected boolean sendFeedback(Player ply, int color, boolean bold, String string){
		ply.sendMessageToClient(ColorStrings.getColoredText(bold, color, ColorStrings.BLACK, string));
		return true;
	}
}
