package saving;

import java.io.File;
import java.io.IOException;

import playermanager.Account;

public class AccountSaver {
	
	private static String ACCOUNTPATH	= "database/accounts/";
	
	protected static boolean doesAccountExist(String name){
		File f = new File(ACCOUNTPATH + name + ".acc");
		if (f.exists()){
			return true;
		}
		return false;
	}
	
	protected static boolean saveAccount(Account acc) throws IOException{
		File file = new File(ACCOUNTPATH);
		
		//Make the folders if they don't exist.
		if (!file.exists()){
			file.mkdirs();
		}
		
		return true;
	}

}
