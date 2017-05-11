package saving;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveManager {

	public static boolean saveAll(){
		try {
			WorldSaver.SaveWorld();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
