package saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import world.Exit;
import world.Map;
import world.MapTile;
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
		ArrayList<Room> rooms = null;
		ArrayList<Exit> exits = null;
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
			out.println("$key " + zn.getZoneID());
			out.println("$name " + zn.getZoneName());
			out.close();
			
			//Setup the minimap file and make sure it exists
			String mmpath = path + zoneID + ".mm";
			file = new File(mmpath);
			if (!file.exists()){
				file.createNewFile();
			}
			
			
			out = new PrintWriter(mmpath);
			Map map = zn.getMap();
			String line = "";
			MapTile tile = null;
			for (int y = 0; y < Map.MAPH; y++){
				for (int x = 0; x < Map.MAPW; x++){
					tile = map.getMapTilePoint(x, y);
					line = line + tile.getfColor() + " " + tile.getbColor() + " " + tile.getBoldForSave() + " " + tile.getSymbol() + " ";
				}
				out.println(line);
				line = "";
			}
			out.close();
			
			//Setup the room file and make sure it exists
			String rmpath = path + zoneID + ".rm";
			file = new File(rmpath);
			if (!file.exists()){
				file.createNewFile();
			}
			
			
			out = new PrintWriter(rmpath);
			rooms = zn.getAllRooms();
			for (int k = 0; k < rooms.size(); k++){
				Room rm = rooms.get(k);
				out.println("$index " + rm.getIndex());
				out.println("$name " + rm.getRoomName());
				out.println("$minimap " + rm.getMapX() + " " + rm.getMapY());
				out.println("$description " + rm.getRoomDescription());
				exits = rm.getExits();
				for (int j = 0; j < exits.size(); j++){
					Exit ex = exits.get(j);
					out.println("$exit" + " " + ex.getName() + " " + ex.getZone() + " " + ex.getRoom());
				}
				out.println();
			}
			out.close();
			
		}
		
		out.close();
		
		return false;
	}
	
}
