package playermanager;

import java.util.ArrayList;

import utility.ColorStrings;
import utility.RandomUtility;

public class LogIn {
	
	private static ArrayList<String> quotes = new ArrayList<String>();

	public static String getLoginScreen(){
		String loginMenu = "\n";
		loginMenu = loginMenu + getQuote() + "\n";
		loginMenu = loginMenu + ColorStrings.replaceTags("$[fGREEN]Enter create <username>.");
		return loginMenu;
	}
	
	public static boolean setupLoginQuotes(ArrayList<String> quoteslist){
		quotes = quoteslist;
		return true;
	}
	
	public static String getQuote(){
		if (quotes.size() == 0){
			return "";
		}
		return quotes.get(RandomUtility.getRandomNumberExclusive(0, quotes.size()));
	}
	
	public static String generatePassword(){
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String password = "";
		for (int i = 0; i < 5; i++){
			password = password + letters.charAt(RandomUtility.getRandomNumber(0, letters.length()));
		}
		return password;
	}
	
}
