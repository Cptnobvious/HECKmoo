package saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import playermanager.Account;
import playermanager.Player;
import utility.StringUtility;

public class PlayerSaver {
	
	private static String ACCOUNTPATH	= "database/accounts/";
	public static final int LOADING_NOERROR		= 0;
	public static final int LOADING_BADPASSWORD	= 1;
	public static final int LOADING_CORRUPT		= 2;
	
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
		out.println("$accflags " + ply.getAccount().getFlags());
		
		//Save the actor stuff
		out.println("$actorname " + ply.getActor().getName());
		
		//Don't forget to clean up
		out.close();
		
		return true;
	}
	
	protected static ArrayList<String> loadPlayer(String name, String pass) throws IOException{
		if (doesAccountExist(name)){
			
			FileReader fr = new FileReader(ACCOUNTPATH + name + ".acc");
			BufferedReader in = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<String>();
			
			String input = in.readLine();
			while (input != null){
				lines.add(input);
				input = in.readLine();
			}
			
			in.close();
			
			//Check password
			for (int i = 0; i < lines.size(); i ++){
				if (StringUtility.getFirstWord(lines.get(i)).equals("$accpass")){
					String testpass = StringUtility.getStringAfterFirst(lines.get(i));
					if (testpass.equals(pass)){
						return lines;
					}
					return null;
				}
			}
			
			return lines;
			
		}
		
		return null;
	}

}
