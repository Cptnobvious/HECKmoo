package textparser;

import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import verb.VerbList;
import verb.adminverbs.ListAdminVerbs;
import verb.globalverbs.ListGlobalVerbs;

//Takes input, compares to verbs, sends them to the verbs

public class TextParser {
	
	static VerbList adminverbs 		= new ListAdminVerbs();
	static VerbList globalverbs 	= new ListGlobalVerbs();
	
	public static boolean Parse(Player ply, String str){
		
		
		String verb = StringUtility.getFirstWord(str).toLowerCase();
		Verb called = null;
		
		//check the admin verbs
		called = adminverbs.getVerb(verb);
		if (called != null){
			called.run(ply, str);
			return true;
		}
		
		//check the global verbs
		called = globalverbs.getVerb(verb);
		if (called != null){
			called.run(ply, str);
			return true;
		}
		
		ply.sendMessageToClient("I don't understand that.");
		return false;
	}

}
