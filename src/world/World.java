package world;

import java.util.ArrayList;

import playermanager.Player;
import utility.ColorStrings;
import world.prefabs.Origin;

public class World {
	
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	
	public static boolean init(){
		if (getZoneByID(Origin.getOriginZone().getZoneID()) == null){
			addZone(Origin.getOriginZone());
		}
		return false;
	}
	
	public static Zone getZoneByID(String id){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(id)){
				return zones.get(i);
			}
		}
		return null;
	}
	
	public static Zone getZoneByIndex(int i){
		return zones.get(i);
	}

	public static int getZoneIndexByID(String id){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(id)){
				return i;
			}
		}
		
		return -1;
	}
	
	public static boolean addZone(Zone zone){
		for (int i = 0; i < zones.size(); i++){
			if (zones.get(i).getZoneID().equals(zone.getZoneID())){
				return false;
			}
		}
		zones.add(zone);
		return true;
	}

	public static Room getRoom(String zone, int index){
		if (getZoneByID(zone) != null){
			return getZoneByID(zone).getRoomByIndex(index);
		}
		return null;
	}
	
	public static Zone getZoneByPlayer(Player ply){
		return getZoneByID(ply.getActor().getCurrentZone());
	}
	
	public static Room getRoomByPlayer(Player ply){
		return getRoom(ply.getActor().getCurrentZone(), ply.getActor().getCurrentRoom());
	}
	
	
	public static ArrayList<String> listAllZones(){
		int i = 0;
		ArrayList<String> zonelist = new ArrayList<String>();
		while(i<zones.size()){
			Zone zn = getZoneByIndex(i);
			zonelist.add(zn.getZoneID());
			zonelist.add(zn.getZoneName() + "\n");
			i++;
		}
		return zonelist;
	}
	
	public static ArrayList<Zone> getAllZones(){
		return zones;
	}
}
