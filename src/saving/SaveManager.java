package saving;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public static ArrayList<String> loadPlayer(String name, String password){
		try {
			return PlayerSaver.loadPlayer(name, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean checkPlayerExists(String name){
		return PlayerSaver.doesAccountExist(name);
	}
	
	
}
