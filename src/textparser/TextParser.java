package textparser;

import java.util.ArrayList;

import chat.ListDefaultChannels;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import verb.VerbList;
import verb.adminverbs.ListAdminVerbs;
import verb.globalverbs.ListGlobalVerbs;

//Takes input, compares to verbs, sends them to the verbs

public class TextParser {
	
	static private VerbList adminverbs 		= new ListAdminVerbs();
	static private VerbList globalverbs 	= new ListGlobalVerbs();
	static private VerbList defaultChannels = new ListDefaultChannels();
	
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
		
		//Check things you're holding
		//Shatter the command into the verb-directobject-preposition-indirectobject setup
		
		//check the chat channels
		called = defaultChannels.getVerb(verb);
		if (called != null){
			called.run(ply, str);
			return true;
		}
		
		ply.sendMessageToClient("I don't understand that.");
		return false;
	}

	public static Verb findVerb(Player ply, String str){
		String verb = StringUtility.getFirstWord(str).toLowerCase();
		Verb called = null;
		
		//check the admin verbs
		called = adminverbs.getVerb(verb);
		if (called != null){
			return called;
		}
		
		//check the global verbs
		called = globalverbs.getVerb(verb);
		if (called != null){
			return called;
		}
		
		//Was this a chat message
		called = defaultChannels.getVerb(verb);
		if (called != null){
			return called;
		}
		
		return null;
	}
	
	public static ArrayList<String> getAllAdminVerbs(){
		return adminverbs.getVerbs();
	}
	
}
