package saving;

import java.io.FileNotFoundException;
import java.io.IOException;

import playermanager.Account;
import playermanager.Player;

public class SaveManager {

	public static boolean saveAll(){
		try {
			WorldSaver.SaveWorld();
			LoginQuoteSaver.makeQuotesFile();
			System.out.println("Successfully saved world");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean loadAll(){
		try {
			WorldSaver.loadWorld();
			LoginQuoteSaver.loadQuotes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean savePlayer(Player ply){
		try {
			return PlayerSaver.savePlayer(ply);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
}
