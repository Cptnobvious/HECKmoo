package saving;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveManager {

	public static boolean saveAll(){
		try {
			WorldSaver.SaveWorld();
			LoginQuoteSaver.makeQuotesFile();
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
}
