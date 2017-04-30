package world;

import java.util.ArrayList;

import world.prefabs.Origin;
import debug.MakeFakeZone;

public class World {
	
	private static ArrayList<Zone> zones = new ArrayList<Zone>();
	
	public static boolean init(){
		addZone(Origin.getOriginZone());
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
	
}
