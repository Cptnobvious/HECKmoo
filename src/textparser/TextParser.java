package textparser;

import items.Inventory;

import java.util.ArrayList;

import chat.ListDefaultChannels;
import playermanager.Player;
import utility.StringUtility;
import verb.Verb;
import verb.VerbDoPrepIo;
import verb.VerbList;
import verb.adminverbs.ListAdminVerbs;
import verb.globalverbs.ListGlobalVerbs;
import world.World;

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
			if (called.hasRequiredFlag(ply)){
				called.run(ply, str);
				return true;
			}
		}
		
		//check the global verbs
		called = globalverbs.getVerb(verb);
		if (called != null){
			if (called.hasRequiredFlag(ply)){
				called.run(ply, str);
				return true;
			}
		}
		
		//Check things you're holding
		//Shatter the command into the verb-directobject-preposition-indirectobject setup
		VerbDoPrepIo vdpi = new VerbDoPrepIo(str);
		if (!vdpi.isMalformed()){
			//Find the direct object and see if we can run on that
			
			//First search player inventory, then search the room
			Inventory invCopy = ply.getActor().getInventory();
			if (invCopy.getItem(vdpi.getDirectObject()) != null){
				if (ply.getActor().getInventory().getItem(vdpi.getDirectObject()).getVerb(vdpi.getVerb()) != null){
					ply.getActor().getInventory().getItem(vdpi.getDirectObject()).getVerb(vdpi.getVerb()).run(ply, str);
					return true;
				}
			}
		}
		
		//check the chat channels
		called = defaultChannels.getVerb(verb);
		if (called != null){
			called.run(ply, str);
			return true;
		}
		
		//Check and see if that was a go command since all else failed
		if (World.getRoomByPlayer(ply).getExitByName(str) != null){
			verb = "go";
		}
		called = globalverbs.getVerb(verb);
		if (called != null){
			called.run(ply, "go " + str);
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
		
		called = globalverbs.getVerb(verb);
		if (called != null){
			return called;
		}
		
		return null;
	}
	
	public static ArrayList<String> getAllVerbs(){
		ArrayList<String> allverbs = new ArrayList<String>();
		ArrayList<String> holder = null;
		
		holder = adminverbs.getVerbs();
		for (int i = 0; i < holder.size(); i++){
			allverbs.add(holder.get(i));
		}
		holder = globalverbs.getVerbs();
		for (int i = 0; i < holder.size(); i++){
			allverbs.add(holder.get(i));
		}
			
			
		return allverbs;
	}
	
}
