package saving;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import playermanager.Account;
import playermanager.Player;

public class PlayerSaver {
	
	private static String ACCOUNTPATH	= "database/accounts/";
	
	protected static boolean doesAccountExist(String name){
		String path = (ACCOUNTPATH + name + ".acc");
		path = path.toLowerCase();
		File f = new File(path);
		if (f.exists()){
			return true;
		}
		return false;
	}
	
	protected static boolean savePlayer(Player ply) throws IOException{
		File file = new File(ACCOUNTPATH);
		
		//Make the folders if they don't exist.
		if (!file.exists()){
			file.mkdirs();
		}
		
		//setup the correct file
		file = new File(ACCOUNTPATH + ply.getAccount().getAccountName() + ".acc");
		if (!file.exists()){
			file.createNewFile();
		}
		
		//Set up the out
		PrintWriter out = new PrintWriter(ACCOUNTPATH + ply.getAccount().getAccountName() + ".acc");
		
		
		//Save account stuff
		out.println("$accname " + ply.getAccount().getAccountName());
		out.println("$accpass " + ply.getAccount().getAccountPassword());
		
		//Save the actor stuff
		out.println("$actorname " + ply.getActor().getName());
		
		//Don't forget to clean up
		out.close();
		
		return true;
	}

}
