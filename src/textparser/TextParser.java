package textparser;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import verb.VerbList;
import verb.adminverbs.ListAdminVerbs;

//Takes input, compares to verbs, sends them to the verbs

public class TextParser {
	
	static VerbList adminverbs = new ListAdminVerbs();
	
	public static boolean Parse(Player ply, String str){
		
		String verb = StringUtility.getFirstWord(str);
		Verb called = null;
		
		//check the admin verbs
		called = adminverbs.getVerb(verb);
		if (called != null){
			called.run(ply, str);
			return true;
		}
		
		ply.sendMessageToClient("I don't understand that.");
		return false;
	}

}
