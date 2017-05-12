package saving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import utility.StringUtility;
import world.Exit;
import world.Map;
import world.MapTile;
import world.Room;
import world.World;
import world.Zone;

public class WorldSaver {

	private static String WORLDZONESPATH 	= "database/world/zones.lst";
	private static String WORLDPATH			= "database/world/";
	
	public static boolean SaveWorld() throws IOException{
		File file = new File(WORLDPATH);
		
		if (!file.exists()){
			file.mkdir();
		}
		
		ArrayList<Zone> zones = World.getAllZones();
		ArrayList<Room> rooms = null;
		ArrayList<Exit> exits = null;
		String zoneID = null;
		PrintWriter out = null;
		
		
		
		//Save the key of all the zones
		file = new File(WORLDZONESPATH);
		out = new PrintWriter(WORLDZONESPATH);
		if (!file.exists()){
			file.createNewFile();
		}
		for (int i = 0; i < zones.size(); i++){
			out.println("$zone " + zones.get(i).getZoneID());
		}
		out.close();
		
		//Start saving off the zones
		for (int i = 0; i < zones.size(); i++){
			//Setup the directory and make sure it exists
			Zone zn = zones.get(i);
			zoneID = zn.getZoneID();
			String path = WORLDPATH + zoneID + "/";
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
				out.println("$room {");
				out.println("$index " + rm.getIndex());
				out.println("$name " + rm.getRoomName());
				out.println("$minimap " + rm.getMapX() + " " + rm.getMapY());
				out.println("$description " + rm.getRoomDescription());
				exits = rm.getExits();
				for (int j = 0; j < exits.size(); j++){
					Exit ex = exits.get(j);
					out.println("$exit" + " \"" + ex.getName() + "\" " + ex.getZone() + " " + ex.getRoom());
				}
				out.println("}");
			}
			out.close();
			
		}
		
		out.close();
		
		return true;
	}
	
	public static boolean loadWorld() throws IOException{
		//Find all the zones that I need to load and stick them in zonesToLoad
		FileReader fr = new FileReader(WORLDZONESPATH);
		BufferedReader in = new BufferedReader(fr);
		ArrayList<String> zonesToLoad = new ArrayList<String>();
		String input = null;
		
		
		input = in.readLine();
		while (input != null){
			String tag = StringUtility.getFirstWord(input);
			String rest = StringUtility.getStringAfterFirst(input);
			if (tag.equals("$zone")){
				zonesToLoad.add(rest);
			}
			input = in.readLine();
		}
		in.close();
		
		//Time to load those zones
		for (int i = 0; i < zonesToLoad.size(); i++){
			//Set up some variables
			Zone zn = new Zone();
			String zoneID = zonesToLoad.get(i);
			String path = WORLDPATH + zoneID + "/";
			
			//Load the zone first
			String zonepath = path + zoneID + ".zn";
			in = new BufferedReader(new FileReader(zonepath));
			input = in.readLine();
			while (input != null){
				String tag = StringUtility.getFirstWord(input);
				String rest = StringUtility.getStringAfterFirst(input);
				if (tag.equals("$key")){
					zn.setZoneID(rest);
				} else if (tag.equals("$name")) {
					zn.setZoneName(rest);
				}
				input = in.readLine();
			}
			in.close();
			
			//Load the minimap now
			//TODO: this will die if someone used a space in a tile's symbol
			String minimapPath = path + zoneID + ".mm";
			ArrayList<String> mmRaw = new ArrayList<String>();
			in = new BufferedReader(new FileReader(minimapPath));
			input = in.readLine();
			while (input != null){
				mmRaw.add(input);
				input = in.readLine();
			}
			in.close();
			Map map = new Map();
			for (int j = 0; j < mmRaw.size(); j++){
				String row = mmRaw.get(j);
				String[] rowArr = row.split(" ");
				for (int k = 0; k < rowArr.length; k = k + MapTile.PARTS){
					int fColor = Integer.parseInt(rowArr[k]); 
					int bColor = Integer.parseInt(rowArr[k + 1]);
					boolean bold = (1 == Integer.parseInt(rowArr[k + 2]));
					String symbol = rowArr[k + 3];
					MapTile tile = new MapTile(fColor, bColor, bold, symbol);
					
					int x = k / MapTile.PARTS;
					int y = j;
					
					map.setMapPoint(x, y, tile);
				}	
			}
			zn.setWholeMap(map);
			
			//Now load the rooms
			String roomPath = path + zoneID + ".rm";
			in = new BufferedReader(new FileReader(roomPath));
			input = in.readLine();
			Room rm = null;
			while (input != null){
				String tag = StringUtility.getFirstWord(input);
				String rest = StringUtility.getStringAfterFirst(input);
				if (tag.equals("$room")){
					rm = new Room();
				} else if (tag.equals("$index")){
					rm.setIndex(Integer.parseInt(rest));
				} else if (tag.equals("$name")){
					rm.setRoomName(rest);
				} else if (tag.equals("$minimap")){
					rm.setMapPos(Integer.parseInt(StringUtility.getWordInString(rest, 1)), Integer.parseInt(StringUtility.getWordInString(rest, 2)));
				} else if (tag.equals("description")){
					rm.setRoomDescription(rest);
				} else if (tag.equals("$exit")){
					String[] args = StringUtility.getWordListWithoutQuotes(rest);
					String name = args[0];
					String zonekey = args[1];
					int index = Integer.parseInt(args[2]);
					Exit ex = new Exit(name, zonekey, index);
					rm.addExit(ex);
				} else if (tag.equals("}")){
					zn.addRoom(rm);
				}
				input = in.readLine();
			}
			in.close();
			
			World.addZone(zn);
		}
		
		return true;
	}
	
}
