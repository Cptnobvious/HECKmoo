package saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import playermanager.LogIn;

public class LoginQuoteSaver {
	
	private static String QUOTESPATH = "database/loginquotes.qt";
	
	public static boolean makeQuotesFile() throws IOException{
		File file = new File(QUOTESPATH);
		
		if (!file.exists()){
			file.createNewFile();
		}
		
		return true;
	}
	
	public static boolean loadQuotes() throws IOException{
		FileReader fr = new FileReader(QUOTESPATH);
		BufferedReader in = new BufferedReader(fr);
		ArrayList<String> quotes = new ArrayList<String>();
		
		String input = null;
		String currQuote = "";
		
		input = in.readLine();
		while (input != null){
			if (input.startsWith("$quote {")){
				currQuote = "";
			} else if (input.equals("}")){
				if (!currQuote.isEmpty()){
					quotes.add(currQuote);
				}
			} else {
				currQuote = currQuote + input + "\n";
			}
			input = in.readLine();
		}
		
		LogIn.setupLoginQuotes(quotes);
		
		return true;
	}

}
