package saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import world.Map;
import world.Room;
import world.World;
import world.Zone;

public class WorldSaver {

	public static boolean SaveWorld() throws IOException{
		File file = new File("database/world/");
		
		if (!file.exists()){
			file.mkdir();
		}
		
		ArrayList<Zone> zones = World.getAllZones();
		ArrayList<Room> rooms = new ArrayList<Room>();
		String zoneID = null;
		PrintWriter out = null;
		
		for (int i = 0; i < zones.size(); i++){
			//Setup the directory and make sure it exists
			Zone zn = zones.get(i);
			zoneID = zn.getZoneID();
			String path = "database/world/" + zoneID + "/";
			file = new File(path);
			if (!file.exists()){
				file.mkdirs();
			}
			
			//Setup the zone file and make sure it exists
			String zonepath = path + zoneID + ".zn";
			file = new File(zonepath);
			if (!file.exists()){
				file.createNewFile();
			}
			
			out = new PrintWriter(zonepath);
			out.println(zn.getZoneID());
			out.println(zn.getZoneName());
			
			//Setup the minimap file and make sure it exists
			String mmpath = path + zoneID + ".mm";
			file = new File(mmpath);
			if (!file.exists()){
				file.createNewFile();
			}
			
			out = new PrintWriter(mmpath);
			Map map = zn.getMap();
			for (int y = 0; y < Map.MAPH; y++){
				for (int x = 0; x < Map.MAPW; x++){
					
				}
			}
		}
		
		out.close();
		
		return false;
	}
	
}
