package textparser;

import playermanager.Player;

//Takes input, compares to verbs, sends them to the verbs

public class TextParser {
	
	public static boolean Parse(Player ply, String str){
		ply.sendMessageToClient("I don't understand that.");
		return false;
	}

}
